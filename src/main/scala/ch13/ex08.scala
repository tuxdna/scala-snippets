package ch13

object ex08 {
  def to2d(a: Array[Int], c: Int) = {
    a.grouped(c).toArray
  }

  def main(args: Array[String]) {
    val arr = Array(1, 2, 3, 4, 5, 6)
    to2d(arr,4) map (_.toList) foreach println
  }

}