package ch14

object ex02 {

  def swap(a: Tuple2[Int, Int]) = {
    a match {
      case (x, y) => (y, x)
    }
  }

  def main(args: Array[String]) {
    println(swap((1, 2)))
  }
}