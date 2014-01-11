package reactive

import scala.language.postfixOps
import rx.lang.scala.{ Observable, Subscription }
import scala.concurrent._
import duration._

object Observer03 extends App {
  type P = (String, String)
  type T = (Long, String, String)
  type S = Seq[T]

  val circles: Array[P] = Array(
    ("Red", "Circle"),
    ("Yellow", "Circle"),
    ("Green", "Circle"),
    ("Aqua", "Circle"),
    ("Blue", "Circle"),
    ("Violet", "Circle"))

  def printOutMarbles(i: Int)(obs: Observable[T])(num: Int)(indent: Int): Unit = {
    blocking { Thread.sleep(20) }
    val is: String = i.toString.padTo(indent, ' ')
    val obsP =
      if (num > 0) obs.take(num)
      else obs
    obsP.subscribe(
      it => {
        val color = it._2
        val shape = it._3
        println(f"$is $color $shape")
      },
      error => println(f"$is Ooops"),
      () => println(f"$is Completed"))
  }

  def printOutBufMarbles(i: Int)(obs: Observable[S])(num: Int)(indent: Int): Unit = {
    val is = i.toString.padTo(indent, ' ')
    blocking { Thread.sleep(20) }
    val obsP = if (num > 0) obs.take(num) else obs

    obsP.subscribe(
      it => {
        val marble0 = it(0)
        val marble1 = it(1)
        val color0 = marble0._2
        val color1 = marble1._2
        val shape0 = marble0._3
        val shape1 = marble1._3
        println(f"$is ($color0 $shape0, $color1 $shape1)")
      },
      error => println(f"$is Ooops"),
      () => println(f"$is Completed"))
  }

  def block(i: Int)(num: Int) = {
    println("Observable: " + i.toString)

    val ticks: Observable[Long] = Observable.interval(500 millis)
    val marbles: Observable[T] = ticks.take(6) map (i => (i, circles(i.toInt)._1, circles(i.toInt)._2))
    val evenMarbles: Observable[T] = marbles.filter(s => (s._1 % 2 == 0))
    val squareMarbles: Observable[T] = marbles.map(s => (s._1, s._2, "Square"))
    val bufMarbles: Observable[Seq[T]] = marbles.buffer(2, 1)

    printOutMarbles(i)(marbles)(num)(1)
    printOutMarbles(i)(evenMarbles)(num)(11)
    printOutMarbles(i)(squareMarbles)(num)(21)
    printOutBufMarbles(i)(bufMarbles)(num)(31)

  }
  val gap = 5000

  block(0)(-1)

  blocking { Thread.sleep(gap) }
  println("Done")

}
