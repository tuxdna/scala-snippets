package ch12

object ex05 extends App {

  def largest(fun: (Int) => Int, input: Seq[Int]) = {
    input map fun reduceLeft ((x, y) => x max y)
  }

  println(largest(x => 10 * x - x * x, 1 to 10))
}
