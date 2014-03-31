package ch21

object ex03 {

  class FactorialNum(val x: Int) {
    def factorial(x: Int): Int = {
      if (x <= 1) 1 else x * factorial(x - 1)
    }
    def `!`() = factorial(x)
  }

  implicit def int2FactorialNum(from: Int) = new FactorialNum(from)

  def main(args: Array[String]): Unit = {
    val z = 5 !

    println(z)
  }
}
