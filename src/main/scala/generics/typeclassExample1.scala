package generics

object typeclassExample1 {

  object Statistics {
    def median(xs: Vector[Double]): Double = xs(xs.size / 2)
    def quartiles(xs: Vector[Double]): (Double, Double, Double) =
      (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))
    def iqr(xs: Vector[Double]): Double = quartiles(xs) match {
      case (lowerQuartile, _, upperQuartile) => upperQuartile - lowerQuartile
    }
    def mean(xs: Vector[Double]): Double = {
      xs.reduce(_ + _) / xs.size
    }
  }

  def main(args: Array[String]) {

    val num = Vector[Double](1, 2, 3, 4, 5, 6, 7, 8, 9)

    val output = (
      Statistics.median(num),
      Statistics.quartiles(num),
      Statistics.iqr(num),
      Statistics.mean(num))

    println(output)

  }

}