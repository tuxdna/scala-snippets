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

object ComposingFuture02 extends App {
  /**
   * Retry successfully completing block at most noTimes
   * and give up after that
   */

  def withTry[T](future: Future[T])(implicit executor: ExecutionContext): Future[Try[T]] = {
    future.map(Success(_)) recover { case t: Throwable => Failure(t) }
  }

  def retry[T](n: Int)(block: => Future[T])(implicit executor: ExecutionContext): Future[T] =
    async {
      var i: Int = 0
      var result: Try[T] = Failure(new Exception("Oops"))
      while (i < n) {
        result = await { withTry(block) }
        result match {
          case Success(s) => { i = i + 1 }
          case Failure(f) => { i = n }
        }
      }
      result.get
    }

  def rb(i: Int) = {
    blocking { Thread.sleep(100 * random.toInt) }
    println("Hi " ++ i.toString)
    i + 10
  }

  def block(i: Int) = {
    println("Iteration: " + i.toString)
    val ri = retry(i)(Future { rb(i) })
    ri onComplete {
      case Success(s) => println(s.toString ++ " = 10 + " ++ i.toString)
      case Failure(t: Exception) => println(t.toString ++ " " ++ i.toString)
      case r => println(r.toString ++ " " ++ i.toString)
    }

  }

  (0 to 4 toList).foreach(i => block(i))
  blocking { Thread.sleep(3000) }
}
