package ch12

object ex03 extends App {

  def fact(n: Int): Int = {
    if (n < 0) -fact(-n)
    else if (n == 0) 1
    else (1 to n) reduceLeft ((x, y) => x * y)
  }

  println((-5 to 5) map (x => (x, fact(x))))
}
