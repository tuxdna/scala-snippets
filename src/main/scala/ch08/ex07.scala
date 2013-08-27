/*
 * 7. Provide a class Square that extends java.awt.Rectangle and has
 * three constructors:
 *  one that constructs a square with a given corner point and width,
 *  one that constructs a square with corner (0, 0) and a given width,
 *  and one that constructs a square with corner (0, 0) and width 0.
 */

object ex07 extends App {
  class Point(val x: Double, val y: Double) {
    override def toString = super.toString() +" -- "+ List(x,y).mkString(",")
  }
  class Square extends java.awt.Rectangle {
    // constructs a square with corner (0, 0) and width 0.
    // this is default constructor
    var corner = new Point(0,0)
    // override var width: Int = 0
    // constructs a square with corner (0, 0) and a given width,
    def this(w: Int) = { 
      this()
      width = w
    }
    // constructs a square with a given corner point and width,
    def this(p: Point, w: Int) = { 
      this(w)
      corner = p
    }
  }
}
