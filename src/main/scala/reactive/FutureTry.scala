package reactive

import math.random
import scala.language.postfixOps
import scala.util._
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.concurrent.{ ExecutionContext, CanAwait, OnCompleteRunnable, TimeoutException, ExecutionException, blocking }

object FutureTry extends App {
  abstract class Try[+T] {
    def flatMap[S](f: T => Try[S]): Try[S] = this match {
      case Success(value) => try { f(value) } catch { case t: Throwable => Failure(t) }
      case failure @ Failure(t) => Failure(t)
    }
  }
  case class Success[+T](elem: T) extends Try[T]
  case class Failure(t: Throwable) extends Try[Nothing]

  object Try {
    def apply[T](r: => T): Try[T] = try { Success(r) } catch { case t: Throwable => Failure(t) }
    def apply[T](f: Future[T]): Future[Try[T]] = {
      f.map(s => Success(s)) recover { case t => Failure(t) }
    }
  }

  def block(i: Int) = {
    def fail = (random < 0.5)
    println(i + "th Iteration: fail? " + fail)
    val f = Future[Int] {
      blocking { Thread.sleep(100 * random.toInt) }
      if (fail)
        throw (new Error("Oooops!"))
      else i + 10
    }
    val ftry: Future[Try[Int]] = Try(f)
    ftry onComplete { case r => println(i + "th " + r.toString) }
  }

  (1 to 10 toList).foreach(i => block(i))
  blocking { Thread.sleep(3000) }
}