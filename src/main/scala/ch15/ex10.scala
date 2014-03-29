package ch15

object ex10 {

  def factorial(n: Int): Int = {
    assert(n >= 0)
    if (n <= 1) 1 else n * factorial(n - 1)
  }

  def main(args: Array[String]) {
    println(factorial(5))

    // Compile with assertions: scalac ex10.scala
    // Compile without assertions: scalac -Xdisable-assertions ex10.scala
    println(factorial(-1))

  }

}