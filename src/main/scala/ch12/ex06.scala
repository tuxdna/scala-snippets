package ch12

object ex06 extends App {

  def largestAt(fun: (Int) => Int, input: Seq[Int]) = {
    (input map (x => (x, fun(x))) reduceLeft ((x, y) => if (x._2 > y._2) x else y))._1
  }

  println(largestAt(x => 10 * x - x * x, 1 to 10))
}
