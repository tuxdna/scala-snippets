package reactive

import math.random
import scala.language.postfixOps
import scala.util._
import scala.util.{ Try, Success, Failure }
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.concurrent.{ ExecutionContext, CanAwait, OnCompleteRunnable, TimeoutException, ExecutionException, blocking }
import scala.async.Async._

object ComposingFuture04 extends App {
  def flatMap[S, T](t: Future[T], f: T => Future[S])(implicit executor: ExecutionContext): Future[S] =
    async { await { f(await { t }) } }

  def block(i: Int) = {
    println("Iteration: " + i.toString)

    def fail = (random < 0.5)
    println(fail.toString)
    val f = Future[Int] {
      blocking { Thread.sleep(100 * random.toInt) }
      if (fail) throw (new Error("Oooops!")) else i + 10
    }

    def fun(i: Int): Future[Int] = Future[Int] {
      blocking { Thread.sleep(100 * random.toInt) }
      if (fail) throw (new Error("Oooops!")) else i * i
    }

    val f2 = flatMap(f, fun)

    f2 onComplete {
      case Success(s) => println(s.toString + " = (10 + " + i.toString + ")^2")
      case Failure(t: NoSuchElementException) => println(t.getMessage.toString + " " + i.toString)
      case Failure(t) => println(t.getCause().toString + " " + i.toString)
    }
  }

  (0 to 10 toList).foreach(i => block(i))
  blocking { Thread.sleep(3000) }
}