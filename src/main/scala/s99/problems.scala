package s99

import scala.annotation.tailrec

object problems {

  @tailrec
  def last(l: List[Int]): Int = {
    l match {
      case x :: Nil => x
      case head :: tail => last(tail)
      case _ => 0
    }
  }

  @tailrec
  def penultimate(list: List[Int]): Int = {
    list match {
      case x :: y :: Nil => x
      case x :: tail => penultimate(tail)
      case _ => 0
    }
  }

  @tailrec
  def nth(n: Int, list: List[Int]): Int = {
    if (n < 0) 0
    else if (n == 0) list.head
    else
      list match {
        case head :: tail => nth(n - 1, tail)
        case _ => 0
      }
  }

  def length(list: List[Int]): Int = {
    list match {
      case Nil => 0
      case x :: xs => 1 + length(xs)
    }
  }

  def reverse(list: List[Int]): List[Int] = {
    def pf(rs: List[Int], rem: List[Int]): List[Int] = {
      rem match {
        case Nil => rs
        case h :: t => pf(h :: rs, t)
      }
    }

    // O(N)
    pf(Nil, list)

    // O(N^2)
    //    list match {
    //      case Nil => Nil
    //      case x :: xs => reverse(xs) ++ List(x)
    //    }
  }

  def isPalindrome(list: List[Int]) = {
    val r = list.reverse
    list.corresponds(r)((x, y) => x == y)
  }

  def flatten(list: List[Any]): List[Int] = {
    list.flatMap { x =>
      x match {
        case l: List[_] => flatten(l)
        case e: Int => List(e)
      }
    }
  }

  def compressNaive(ls: List[Symbol]) = {
    var a = ls.head
    var l = List[Symbol](a)
    for (b <- ls.tail) {
      if (a != b) {
        a = b
        l = l ++ List(b)
      }
    }
    l
  }

  def compress(ls: List[Symbol]) = {
    def pf(rs: List[Symbol], rem: List[Symbol]): List[Symbol] = {
      rem match {
        case Nil => rs
        case x :: xs => pf(rs ++ List(x), rem.dropWhile(_ == x))
      }
    }
    val a = ls.head
    val rs = List[Symbol](a)
    pf(rs, ls.dropWhile(_ == a))
  }

  def pack(ls: List[Symbol]) = {
    def pf(rs: List[List[Symbol]], rem: List[Symbol]): List[List[Symbol]] = {
      rem match {
        case Nil => rs
        case x :: xs => {
          val newRs = rs ++ List(rem.takeWhile(_ == x))
          pf(newRs, rem.dropWhile(_ == x))
        }
      }
    }
    val a = ls.head
    val as = ls.takeWhile(_ == a)
    pf(List(as), ls.dropWhile(_ == a))
  }

  def encode(ls: List[Symbol]) = {
    val p = pack(ls)
    p.map { x =>
      (x.size, x(0))
    }
  }

  def main(args: Array[String]) {
    val list = List(1, 1, 2, 3, 5, 8)
    val p01 = last(list)
    println(p01)

    val p02 = penultimate(list)
    println(p02)

    val p03 = nth(2, list)
    println(p03)

    val p04 = length(list)
    println(p04)

    val p05 = reverse(list)
    println(p05)

    val p06 = isPalindrome(List(1, 2, 3, 2, 1))
    println(p06)

    val ls = List(List(1, 1), 2, List(3, List(5, 8)))
    val p07 = flatten(ls)
    println(p07)

    val unc = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    val p08 = compress(unc)
    println(p08)

    val p09 = pack(unc)
    println(p09)

    val p10 = encode(unc)
    println(p10)

  }
}