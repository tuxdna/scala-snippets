package ch12

import scala.util.Random

object ex02 extends App {
  val a = Array(1, 10, 4, 7, 89, 23)
  val m = a reduceLeft ((x, y) => x max y)
  println(m)
}
