package ch12

import scala.collection.GenSeq

object ex09 extends App {

  val a = Array("Hello", "World")
  val b = Array("hello", "world", "people")
  println(a.zip(a))
  println(b.zip(b))
  println(a.zip(b))
  println(b.zip(a))

  def corresponds[A, B](self: GenSeq[A], that: GenSeq[B], p: (A, B) => Boolean): Boolean = {
    val i = self.iterator
    val j = that.iterator
    while (i.hasNext && j.hasNext)
      if (!p(i.next, j.next))
        return false

    !i.hasNext && !j.hasNext
  }

  // TODO:
  val c = true // corresponds(a, b, (x, y) => x.equalsIgnoreCase(y))
  println(c)

  val length = Array(5, 5)
  val d = a.corresponds(length)(_.length == _)
  println(d)

}
