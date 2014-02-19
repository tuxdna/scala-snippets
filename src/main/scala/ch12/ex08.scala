package ch12

object ex08 extends App {

  val a = Array("Hello", "World")
  val b = Array("hello", "world")
  val c = a.corresponds(b)(_.equalsIgnoreCase(_))
  println(c)
  val length = Array(5, 5)
  val d = a.corresponds(length)(_.length == _)
  println(d)

}
