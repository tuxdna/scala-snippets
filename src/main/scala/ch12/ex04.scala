package ch12

object ex04 extends App {
  def fact(n: Int): Int = {
    if (n < 0) -fact(-n)
    else if (n == 0) 1
    else (1 to n).foldLeft(1)((a, b) => a * b)
  }

  println((-5 to 5) map (x => (x, fact(x))))

}