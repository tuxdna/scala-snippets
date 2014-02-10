import scala.concurrent._
import java.util.Random
import ExecutionContext.Implicits.global

object advance extends App {

  class Fraction(val n: Int, val d: Int) {
    override def toString = "Fraction(" + n + ", " + d + ")"
  }

  object Fraction {
    def apply(n: Int, d: Int) = new Fraction(n, d)

    def unapply(f: Fraction) = if (f.d == 0) None else Some(f.n, f.d)
  }

  implicit def int2Fraction(n: Int) = Fraction(n, 1)

  implicit def fraction2Int(f: Fraction): Int = f match {
    case Fraction(n, d) => n
  }

  // implicit def fraction2String(f: Fraction): String = "Fraction("+f.n+", "+f.d+")"

  val result: Fraction = 3 + Fraction(4, 5)

  println(result)

  // Sometimes we need recover from an exception with a value
  // lets `recover` from an exception with a fallback val 'Infinity'

  val tryDivideByZeroAgain = future {
    Thread.sleep(1000)
    1 / 0
  } recover {
    case e: ArithmeticException => "Infinity"
  }

  tryDivideByZeroAgain onSuccess {
    case e => Console.println(e)
  }

  tryDivideByZeroAgain onFailure {
    case e => Console.println(e)
  }

  Console.println("Try dividing by zero, recover from exception..")

  Thread.sleep(2000)

  // output
  // Try dividing by zero, recover from exception..
  // Infinity

  // Or maybe future f1 must fallback to future f2?

  val f1 = future {
    Thread.sleep(500)
    1 / 0
  }

  val f2 = future {
    Thread.sleep(500)
    "Infinity"
  }

  f1 fallbackTo f2 onSuccess {
    case v => Console.println(v)
  }

  Console.println("Try dividing by zero, fallback to another future..")

  Thread.sleep(2000)

  // output
  // Try dividing by zero, fallback to another future..
  // Infinity

  // the for {} construct lets us execute multiple futures in parallel
  // to serially execute futures in specific orders, we use `andThen`
  // andThen ensures execution orders in what would otherwise be random

  val whamBamThankYouMaam = future {
    Thread.sleep(500)
    Console.println("Wham!")
  } andThen {
    case _ => {
      Thread.sleep(500)
      Console.println("Bam!")
    }
  } andThen {
    case _ => {
      Thread.sleep(500)
      Console.println("Thank you ma'am!")
    }
  }

  Console.println("Will you score?")

  Thread.sleep(2000)

  // output
  //
  // Will you score?
  // Wham!
  // Bam!
  // Thank you ma'am!

  // `promises` can be used to compose type safe futures

  val willYouMarryMe = promise[Boolean]

  willYouMarryMe.future onSuccess {
    case yes => Console.println("Yes! :D")
  }

  willYouMarryMe.future onFailure {
    case no => Console.println("No :(")
  }

  future {
    Thread.sleep(1000)
    if (new Random().nextBoolean())
      willYouMarryMe success true // try passing non boolean value here
    else
      willYouMarryMe failure new Exception
  }

  Console.println("Will you marry me?")

  Thread.sleep(2000)

  // output
  //
  // Will you marry me?
  // Yes! :D | No :(

  // Two asynchronous blocks `tryComplete` a promise

  val whoWonTheRace = promise[String]

  whoWonTheRace.future onSuccess {
    case name => Console.println(name + " wins")
  }

  future {
    Thread.sleep(new Random().nextInt(500))
    whoWonTheRace trySuccess "x"
  }

  future {
    Thread.sleep(new Random().nextInt(500))
    whoWonTheRace trySuccess "y"
  }

  Console.println("Who won the race?")

  Thread.sleep(1000)

  // output
  //
  // Who won the race?
  // x wins | y wins

}