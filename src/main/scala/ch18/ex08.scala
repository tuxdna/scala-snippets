package ch18

object ex08 {

  def printValues(f: { def apply(x: Int): Int }, from: Int, to: Int) = {
    val x = for (i <- from to to) yield f(i)
    println(x)
  }

  def main(args: Array[String]): Unit = {
    printValues((x: Int) => x * x, 3, 6)
    printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)
  }

}