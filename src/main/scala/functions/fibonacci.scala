package functions

import scala.annotation.tailrec

object fibonacci {

  /*
   * Write a function that calls the fibonacciList function with a 
   * value of 1000. 
   * Print to the console every item in the list that is divisible by 3.
   * 
   */
  def fibonacciList(x: Int): List[Int] = {
    var output = List[Int]()

    def fibonacciList(x: Int, val1: Int, val2: Int): Int = {
      val nextVal = val1 + val2
      if (nextVal < x) {
        output = output ++ List(nextVal)
        fibonacciList(x, val2, nextVal)
      } else 0 // terminate
    }

    fibonacciList(x, 0, 1)

    output
  }

  // tail recursive version
  def fibonacciList2(x: Int): List[Int] = {
    @tailrec
    def fibonacciList(x: Int, val1: Int, val2: Int, partialList: List[Int]): List[Int] = {
      val nextVal = val1 + val2
      if (nextVal < x) {
        fibonacciList(x, val2, nextVal, partialList ++ List(nextVal))
      } else partialList
    }

    val output2 = fibonacciList(x, 0, 1, List(1))
    output2
  }

  def main(args: Array[String]) {
    // non-tr
    println("Recursive")
    fibonacciList(1000).filter(x => x % 3 == 0) foreach println
    // tr
    println("Tail recursive")
    fibonacciList2(1000).filter(x => x % 3 == 0) foreach println
  }

}