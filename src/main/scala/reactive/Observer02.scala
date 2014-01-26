package reactive

import scala.language.postfixOps
import rx.lang.scala.Observable
import scala.concurrent._
import duration._

object Observer02 extends App {
  def printOut[T](i: Int)(obs: Observable[T])(num: Int)(msg: String): Unit = {
    blocking { Thread.sleep(20) }
    val is = i.toString
    val obsP = if (num > 0) obs.take(num) else obs
    obsP.subscribe(
      it => {
        val itOut = it.toString
        println(f"i = $is, obs: $msg, next = $itOut")
      },
      error => println(f"$is Ooops"),
      () => println(f"$is Completed"))
  }

  def block(i: Int)(num: Int) = {
    println("Observable: " + i.toString)

    val ticks: Observable[Long] = Observable.interval((i * 1000 + 500) millis)
    val evens: Observable[Long] = ticks.filter(s => (s % 2 == 0))
    val bufs: Observable[Seq[Long]] = ticks.buffer(2, 1)
    def errorfn: Long = {
      new Exception("oops")
      0
    }
    val fails: Observable[Long] = ticks.take(3) ++ Observable.from(List(errorfn)) ++ ticks

    printOut(i)(ticks)(num)("ticks")
    printOut(i)(evens)(num)("evens")
    printOut(i)(bufs)(num)("bufs")
    printOut(i)(fails)(num)("fails")
  }

  val gap = 5000

  (0 to 1 toList).foreach(i => block(i)(-1))

  blocking { Thread.sleep(gap) }
  println("Done")

}