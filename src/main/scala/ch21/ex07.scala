package ch21
import java.awt.Point

object ex07 {

  def distanceFromOrigin(p: Point) = math.sqrt(p.x * p.x + p.y * p.y)

  class LexOrderedPoint(x: Int, y: Int) extends Point with Ordered[Point] {
    def compare(that: Point) = {
      if (LexOrderedPoint.this.x == that.x) LexOrderedPoint.this.y - that.y
      else LexOrderedPoint.this.x - that.x
    }
  }

  class DistanceOrderedPoint(x: Int, y: Int) extends Point with Ordered[Point] {
    def compare(that: Point) = {
      distanceFromOrigin(DistanceOrderedPoint.this) compare distanceFromOrigin(that)
    }
  }

  // implicit def point2OrderedPoint(point: Point) = new LexOrderedPoint(point.x, point.y)
  implicit def point2DistanceOrderedPoint(point: Point) = new DistanceOrderedPoint(point.x, point.y)

  def main(args: Array[String]) {
    val points = for (x <- List(4, 1, 5); y <- List(7, 6)) yield new Point(x, y)
    println(points)
    val sorted = points.sorted
    println(sorted)

    val p1 = new Point(1, 2)
    val p2 = new Point(1, 3)
    println(if (p1 < p2) p1 else p2)
  }
}