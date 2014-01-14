package ch12

object ex07 extends App {
  val pairs = (1 to 10) zip (11 to 20)
  println(pairs)
 
  def adjustToPair(fun: (Int, Int) => Int)(t: Tuple2[Int, Int]) = {
    fun(t._1, t._2)
  }
  println(pairs.map(adjustToPair(_ + _)))
  println(pairs.map(adjustToPair(_ * _)))
}