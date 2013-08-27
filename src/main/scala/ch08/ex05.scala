package ch08

/*
 * 5. Design a class Point whose x and y coordinate values can be
 * provided in a constructor. Provide a subclass LabeledPoint whose
 * constructor takes a label value and x and y coordinates, such as
 *   new LabeledPoint("Black Thursday", 1929, 230.07)
 */


object ex05 extends App {
  class Point(var x: Double, val y: Double)
  class LabeledPoint(val label: String, x: Double, y: Double
		   ) extends Point(x,y)
  
  val lp1 = new LabeledPoint("Black Thursday", 1929, 230.07)
  println(lp1.label -> ( lp1.x, lp1.y ))
  lp1.x = 10 // should reflect in the parent class
  println(lp1.label -> ( lp1.x, lp1.y ))

  val p1: Point = lp1
  println(p1.x, p1.y)
}
