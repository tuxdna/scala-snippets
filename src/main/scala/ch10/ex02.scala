package ch10

import java.awt.Point
import scala.util.Random

object ex02 extends App {
  class OrderedPoint(x: Int, y: Int) extends Point(x, y) with Ordered[OrderedPoint] {
    def compare(o: OrderedPoint) = { if (x == o.x) y - o.y else x - o.x }
    override def toString = s"OrderedPoint[x=$x,y=$y]"
  }
  val r = new Random
  val points = (1 to 5).map { i => new OrderedPoint(r.nextInt(5), r.nextInt(20)) }
  println("Random Order")
  points foreach println
  println
  println("Sorted Order")
  points.sorted foreach println
}
