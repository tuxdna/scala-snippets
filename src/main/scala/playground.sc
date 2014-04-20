import scala.util.{ Try, Success, Failure }

object temp {
  def createArray(n: Int): Array[Int] = {
    var x = new Array[Int](n)
    for (i <- 0 to x.length - 1)
      x(i) = scala.util.Random.nextInt(n)
    x
  }                                               //> createArray: (n: Int)Array[Int]

  class kid {
    def on = new kid
    def the(block: => Unit) = { block; "Scala is awesome!" }
  }

  println((new kid on) the {})                    //> Scala is awesome!

}