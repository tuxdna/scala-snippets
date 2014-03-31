package ch21

import java.awt.Point

object ex06 {
  class LexOrderedPoint(x: Int, y: Int) extends Point with Ordered[Point] {
    def compare(that: Point) = {
      if (LexOrderedPoint.this.x == that.x) LexOrderedPoint.this.y - that.y
      else LexOrderedPoint.this.x - that.x
    }
  }
  implicit def point2OrderedPoint(point: Point) = new LexOrderedPoint(point.x, point.y)

  def main(args: Array[String]) {
    val points = for (x <- List(4, 1, 5); y <- List(7, 6)) yield new Point(x, y)
    println(points)
    println(points.sorted)

    val p1 = new Point(1, 2)
    val p2 = new Point(1, 3)
    println(if (p1 < p2) p1 else p2)
  }
}
