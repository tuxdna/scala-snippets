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

object ComposingFuture05 {

  def filter[T](future: Future[T], predicate: T => Boolean)(implicit executor: ExecutionContext): Future[T] = {
    val p = Promise[T]()
    future.onComplete {
      case Success(s) => {
        if (!predicate(s)) p.failure(new NoSuchElementException("No such element"))
        else p.success(s)
      }
      case Failure(f) => { p.failure(f) }
    }
    p.future
  }

  def block(i: Int) = {
    println("Iteration: " + i.toString)
    def fail = (random < 0.5)
    println(fail.toString)

    val f = Future[Int] {
      blocking { Thread.sleep(100 * random.toInt) }
      if (fail) throw (new Error("Oooops!")) else i + 10
    }

    val f2 = filter[Int](f, (i => (i < 12)))

    f2 onComplete {
      case Success(s) => println(s.toString ++ " = 10 + " ++ i.toString)
      case Failure(t: NoSuchElementException) => println(t.getMessage.toString ++ " " ++ i.toString)
      case Failure(t) => println(t.getCause().toString ++ " " ++ i.toString)
    }

  }
  (0 to 4 toList).foreach(i => block(i))
  blocking { Thread.sleep(3000) }

}