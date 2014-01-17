package ch13
import scala.collection.mutable

object ex03 extends App {
  val lst = mutable.LinkedList(1, -2, 7, -9, 23, -4, -5, 8)
  println(lst)

  var cur = lst
  while (cur != Nil) {
    if (cur.elem < 0) {
      cur.elem = 0

    }
    cur = cur.next
  }

  println(lst)
  cur = lst
  while (cur != Nil && cur.next != Nil) {
    cur.next = cur.next.next
    cur = cur.next
  }

  println(lst)

//  def removeZeros(lst: mutable.LinkedList[Int]) = {
//    var prev = lst
//    var cur = prev.next
//    while (cur != Nil) {
//      if (cur.elem == 0) {
//        prev.next = cur.next
//      }
//      cur = prev.next
//      prev = 
//      cur = cur.next
//    }
//
//  }

  val lst2 = scala.collection.mutable.LinkedList(1, -2, 0, -9, 23, 0, -5, 8)
  println(lst2)

  val lst3 = lst2.filter(_ != 0)
  println(lst3)
}
