package generics

object typeclassExample3 {

  object Mathematics {
    import annotation.implicitNotFound

    // @implicitNotFound("No member of type class NumberLike in scope for ${T}")
    trait NumberLike[T] {
      def plus(x: T, y: T): T
      def divide(x: T, y: Int): T
      def minus(x: T, y: T): T
    }

    object NumberLike {
      implicit object NumberLikeDouble extends NumberLike[Double] {
        def plus(x: Double, y: Double): Double = x + y
        def divide(x: Double, y: Int): Double = x / y
        def minus(x: Double, y: Double): Double = x - y
      }
      implicit object NumberLikeInt extends NumberLike[Int] {
        def plus(x: Int, y: Int): Int = x + y
        def divide(x: Int, y: Int): Int = x / y
        def minus(x: Int, y: Int): Int = x - y
      }
    }
  }

  object Statistics {
    import Mathematics.NumberLike

    def median[T](xs: Vector[T]): T = xs(xs.size / 2)

    def quartiles[T](xs: Vector[T]): (T, T, T) =
      (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))

    def iqr[T](xs: Vector[T])(implicit ev: NumberLike[T]): T = quartiles(xs) match {
      case (lowerQuartile, _, upperQuartile) => ev.minus(upperQuartile, lowerQuartile)
    }

    def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T = {
      val sum = xs.reduce((x, y) => ev.plus(x, y))
      ev.divide(sum, xs.size)
    }

    def statall[T](num: Vector[T])(implicit ev: NumberLike[T]) = {
      (median(num), quartiles(num), iqr(num), mean(num))
    }
  }

  object MyStringOps {
    import Mathematics.NumberLike

    implicit object NumberLikeDuration extends NumberLike[String] {
      def plus(x: String, y: String): String = x + y
      def divide(x: String, y: Int): String = x.grouped(y).mkString(",")
      def minus(x: String, y: String): String = x.replace(y, "")
    }
  }

  def main(args: Array[String]) {
    val num1 = Vector(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(Statistics.statall(num1))

    val num2 = Vector[Double](1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(Statistics.statall(num2))

    import MyStringOps._

    val num3 = Vector[String]("today", "is", "a", "good", "day", "to", "learn", "type", "classes")
    println(Statistics.statall(num3))

  }
}
