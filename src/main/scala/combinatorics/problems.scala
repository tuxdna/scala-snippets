package combinatorics

// Based on this article:
// http://vkostyukov.ru/posts/combinatorial-algorithms-in-scala/

object problems {

  object IntOps {
    implicit class ExtendedInt(n: Int) {
      def times(fn: => Unit) {
        (1 to n) foreach (x => fn)
      }
    }
  }

  object CombinatorialOps {
    implicit class CombinatorialList[A](l: List[A]) {
      def xcombinations(n: Int): List[List[A]] = {
        l match {
          case x if n > l.length => Nil
          case x if n == 1 => x map (List(_))
          case x :: xs => xs.xcombinations(n - 1).map(a => x :: a).:::(xs.xcombinations(n))
          case x => Nil
        }
      }
      def xsubsets(): List[List[A]] = {
        (2 to l.length).foldLeft(xcombinations(1))((a, b) => a ::: l.xcombinations(b))
      }
      def xvariations(n: Int): List[List[A]] = {
        l match {
          case x if n > l.length => Nil
          case x if n == 1 => x map (List(_))
          case x :: xs => {
            val mix = xs.xvariations(n - 1).flatMap { a =>
              (0 to a.length) map { split =>
                val (p, q) = a.splitAt(split)
                (p ::: List(x)) ::: q
              }
            }

            val remaining = xs.xvariations(n)
            mix ::: remaining
          }
          case x => Nil
        }
      }
      def xpermutations(): List[List[A]] = {
        (2 to l.length).foldLeft(xvariations(1))((a, b) => a ::: l.xvariations(b))
      }
    }
  }

  def main(args: Array[String]): Unit = {
    import IntOps._
    5.times { println("Hello") }

    import CombinatorialOps._
    println("xcombinations")
    println(List(1, 2).xcombinations(0))
    println(List(1, 2).xcombinations(2))
    println(List(1, 2).xcombinations(3))
    println(List(1, 2, 3).xcombinations(2))
    println("xsubsets")
    println(List(1, 2, 3).xsubsets)
    println("xvariations")
    println(List(1, 2).xvariations(0))
    println(List(1, 2).xvariations(3))
    println(List(1, 2).xvariations(2))
    println("xpermutations")
    List(1, 2, 3, 4).xpermutations foreach println

  }
}

