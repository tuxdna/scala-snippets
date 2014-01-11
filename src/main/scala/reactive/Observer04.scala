package reactive

import scala.language.postfixOps
import rx.lang.scala.{ Observable, Subscription }
import scala.concurrent._
import duration._

object Observer04 extends App {
  type P = (String, String)
  type T = (Long, String, String)
  type S = Seq[T]

  val circles: Array[P] = Array(
    ("Red", "Circle"),
    ("Yellow", "Circle"),
    ("Green", "Circle"),
    ("Aqua", "Circle"),
    ("Blue", "Circle"),
    ("Violet", "Circle")
  )

  def printOutMarbles(i: Int)(obs: Observable[T])(num: Int)(indent: Int): Unit = {
    blocking { Thread.sleep(20) }
    val is: String = i.toString.padTo(indent, ' ')

    val obsP = if (num > 0) obs.take(num) else obs

    obsP.subscribe(
      it => {
        val color = it._2
        val shape = it._3
        println(f"$is $color $shape")
      },
      error => println(f"$is Ooops"),
      () => println(f"$is Completed"))
  }

  def block(i: Int)(num: Int) = {
    println("Observable: " + i.toString)
    val ticks: Observable[Long] = Observable.interval(500 millis)
    val marbles: Observable[T] = ticks.take(6).map(i => (i, circles(i.toInt)._1, circles(i.toInt)._2))
    val squareMarbles: Observable[T] = marbles.map(s => (s._1, s._2, "Square"))
    val fails: Observable[T] = marbles.take(3) ++ Observable(new Exception("My Bad")) ++ squareMarbles
    val eReturn: Observable[T] = fails.onErrorReturn(e => (-99, "Black", "Diamond"))
    val eResume: Observable[T] = fails.onErrorResumeNext(squareMarbles)

    printOutMarbles(i)(marbles)(num)(1)
    i match {
      case 0 => printOutMarbles(i)(fails)(num)(11)
      case 1 => printOutMarbles(i)(eReturn)(num)(21)
      case 2 => printOutMarbles(i)(eResume)(num)(31)
    }
  }

  val gap = 5000
  block(0)(-1)

  blocking { Thread.sleep(gap) }
  block(1)(-1)

  blocking { Thread.sleep(gap) }
  block(2)(-1)

  blocking { Thread.sleep(gap) }

  println("Done")

}
