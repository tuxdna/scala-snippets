package ch15

import scala.annotation.tailrec

object ex07 {

  class Util {
    @tailrec
    final def sum2(xs: Seq[Int], partial: BigInt): BigInt = {
      if (xs.isEmpty) partial else sum2(xs.tail, partial + xs.head)
    }
  }

  def main(args: Array[String]): Unit = {
    val u = new Util

    println(u.sum2(1 to 1000000, 0))
  }

}