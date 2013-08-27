package ch06

// 4. Define a Point class with a companion object so that you can
// construct Point instances as Point(3, 4), without using new.

object ex04 extends App {
  class Point(x:Int, y:Int) {
    override def toString = { super.toString + " (%d, %d)".format(x,y) }
  }
  object Point { 
    def apply(x:Int, y:Int) = new Point(x,y)
  }

  println(Point(1,2))
}
