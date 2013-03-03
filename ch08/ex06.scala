/*
 * 6. Define an abstract class Shape with an abstract method
 * centerPoint and subclasses Rectangle and Circle. Provide
 * appropriate constructors for the subclasses and override the
 * centerPoint method in each subclass.
 */

object ex06 extends App {
  abstract class Shape {
    def centerPoint
  }

  class Point(val x: Double, val y: Double) {
    override def toString = super.toString() +" -- "+ List(x,y).mkString(",")
  }

  class Rectangle(topLeft: Point, bottomRight: Point) extends Shape {
    var _cp: Point = new Point( (topLeft.x + bottomRight.x)/2,
		      (topLeft.y + bottomRight.y)/2 )
    def centerPoint =  {
      println(_cp)
    }
  }

  class Circle(center: Point) extends Shape {
    def centerPoint = {
      println(center)
    }
  }

  val r1 = new Rectangle(new Point(0,0), new Point(10,20))
  r1.centerPoint
  
  val c1 = new Circle(new Point(10,10))
  c1.centerPoint
}
