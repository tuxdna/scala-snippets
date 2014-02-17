package functions

object fp {

  /**
   * Sum of all numbers in a list
   * @param numbers
   * @return
   */
  def sum(numbers: List[Double]) = numbers.sum

  /**
   * Mean or average of all numbers in a list
   * @param numbers
   * @return
   */
  def mean(numbers: List[Double]) = numbers.sum / numbers.length

  /**
   * the one with highest frequency
   * @param numbers
   * @return
   */
  def mode(numbers: List[Double]) = {
    var frequencyMap = Map[Double, Int]()
    numbers.foreach { n =>
      val freq = frequencyMap.getOrElse(n, 1)
      frequencyMap = frequencyMap + (n -> freq)
    }

    val fm = numbers./:(Map.empty[Double, Int]) { (z, a) =>
      val count = (1 + z.getOrElse(a, 0))
      z ++ Map(a -> count)
    }
    // sort by frequency
    // take first ( value -> frequency) pair
    // and return value
    fm.toList.sortBy(x => x._2).reverse.head._1
  }

  /**
   * the one in middle of an ordered list of numbers
   * @param numbers
   * @return
   */
  def median(numbers: List[Double]) = {
    val sorted = numbers.sorted
    println(sorted)
    if (numbers.length % 2 == 0) { // even length
      val m1 = numbers.length / 2
      val m2 = m1 - 1
      (sorted(m1) + sorted(m2)) / 2
    } else { // odd length
      sorted((numbers.length - 1) / 2)
    }
  }

  /**
   * Calculate Standard Deviation
   * @param numbers
   * @return
   */
  def standardDeviation(numbers: List[Double]) = {
    val sum = numbers.sum
    val mean = sum / numbers.length
    val squareSum = numbers.foldLeft(0.0)((z, a) => z + (mean - a) * (mean - a))
    Math.sqrt(squareSum / numbers.length)
  }

  /**
   * A higher order function to calculate statistic
   * @param numbers
   * @param statfun
   * @return
   */
  def statistic(statfun: List[Double] => Double)(numbers: List[Double]): Double = {
    statfun(numbers)
  }

  def main(args: Array[String]) = {
    val nums = List(5.0, 6.4, 1.0, 5.2, 5.5, 1.0, 5.5, 3.2, 1.0, 1.0, 3.2)
    println("numbers: " + nums)
    println("sum: " + sum(nums))
    println("mean: " + mean(nums))
    println("mode: " + mode(nums))
    println("median: " + median(nums))
    println("standard deviation: " + standardDeviation(nums))

    val s1 = statistic(mean) _
    
    val s1result = s1(List(1, 2, 3, 5, 8))
    println("s1result: " + s1result)
  }
}
