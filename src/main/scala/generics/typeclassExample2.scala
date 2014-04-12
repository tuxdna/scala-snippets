package generics

object typeclassExample2 {

  object Statistics {
    def median[T](xs: Vector[T]): T = xs(xs.size / 2)

    def quartiles[T](xs: Vector[T]): (T, T, T) =
      (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))

    def iqr[T](xs: Vector[T])(implicit ev: Numeric[T]): T = quartiles(xs) match {
      case (lowerQuartile, _, upperQuartile) => ev.minus(upperQuartile, lowerQuartile)
    }

    def mean[T](xs: Vector[T])(implicit ev: Numeric[T]): Double = {
      val sum = xs.reduce((x, y) => ev.plus(x, y))
      val m = ev.toDouble(sum) / xs.size
      m
    }
  }

  def main(args: Array[String]): Unit = {
    val num = Vector(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val output = (
      Statistics.median(num),
      Statistics.quartiles(num),
      Statistics.iqr(num),
      Statistics.mean(num))

    println(output)
  }

}