package ch18

import scala.util.Either
import scala.annotation.tailrec

object ex06 {

  def getValue(array: Array[Int], n: Int): Int Either Int= {
    @tailrec
    def binarySearch(array: Array[Int], lower: Int, upper: Int, closest: Int): Tuple2[Int, Int] = {
      if (upper < lower) {
        (-1, closest)
      } else {

        val mid = (upper + lower) / 2
        val midValue = array(mid)
        if (midValue == n) (mid, midValue)
        else {
          if (midValue < n) {
            binarySearch(array, mid + 1, upper, midValue)
          } else {
            binarySearch(array, lower, mid - 1, midValue)
          }
        }
      }
    }

    val rv = binarySearch(array, 0, array.length - 1, 0)
    val (idx, value) = rv
    if (idx == -1) Left(value) else Right(value)
  }

  def main(args: Array[String]) {
    val array = Array(1, 2, 5, 5, 9, 11)
    for (x <- 1 to 10) {
      println(x + ": " + getValue(array, x))
    }
  }

}
