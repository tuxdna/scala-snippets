package reactive

import math.random
import scala.language.postfixOps
import scala.util._
import scala.util.{ Try, Success, Failure }
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.concurrent.{ ExecutionContext, CanAwait, OnCompleteRunnable, TimeoutException, ExecutionException, blocking }

object ComposingFuture01 extends App {

  def retry[T](tries: Int)(block: => Future[T]): Future[T] = {
    if (tries == 0) {
      Future.failed(new Exception("Sorry"))
    } else {
      block fallbackTo {
        retry(tries - 1) { block }
      }
    }
  }

  def retryWithFoldLeft[T](tries: Int)(block: => Future[T]): Future[T] = {
    val tr = (1 to tries).iterator
    val attempts = tr map (_ => () => block)
    val failed: Future[T] = Future.failed(new Exception)
    attempts.foldLeft(failed)((a, block) => a fallbackTo { block() })
  }

  def retryWithFoldRight[T](tries: Int)(block: => Future[T]): Future[T] = {
    val tr = (1 to tries).iterator
    val attempts = tr map (_ => () => block)
    val failed: Future[T] = Future.failed(new Exception)
    attempts.foldRight(failed)((block, a) => block() fallbackTo a)
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

  def blockFoldLeft(i: Int) = {
    println("Iteration: " + i.toString)
    val ri = retryWithFoldLeft(i)(Future { rb(i) })
    ri onComplete {
      case Success(s) => println(s.toString ++ " = 10 + " ++ i.toString)
      case Failure(t: Exception) => println(t.toString ++ " " ++ i.toString)
      case r => println(r.toString ++ " " ++ i.toString)
    }
  }

  def blockFoldRight(i: Int) = {
    println("Iteration: " + i.toString)
    val ri = retryWithFoldRight(i)(Future { rb(i) })
    ri onComplete {
      case Success(s) => println(s.toString ++ " = 10 + " ++ i.toString)
      case Failure(t: Exception) => println(t.toString ++ " " ++ i.toString)
      case r => println(r.toString ++ " " ++ i.toString)
    }
  }

//  (0 to 4 toList).foreach(i => block(i))
//  blocking { Thread.sleep(3000) }
//
//  (0 to 4 toList).foreach(i => blockFoldLeft(i))
//  blocking { Thread.sleep(3000) }
//
  (0 to 4 toList).foreach(i => blockFoldRight(i))
  blocking { Thread.sleep(3000) }

}