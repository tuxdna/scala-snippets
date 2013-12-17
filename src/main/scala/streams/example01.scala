package streams

object example01 extends App {

  def fibFrom(a: Int, b: Int): Stream[Int] =
    a #:: fibFrom(b, a + b) //> fibFrom: (a: Int, b: Int)Stream[Int]

  val fib = fibFrom(1, 1).take(7).toList
  println(fib)

}