package ch13

object ex07 {
  def main(args: Array[String]) {
    val prices = List(5.0, 20.0, 9.99)
    val quantities = List(10, 2, 1)
    val net1 = (prices zip quantities) map (p => p._1 * p._2)
    println(net1 sum)

    val f = ((x: Double, y: Int) => x * y).tupled
    val net2 = (prices zip quantities) map (f)
    println(net2 sum)

    val net3 = (prices, quantities).zipped.map(_ * _)
    println(net3 sum)

  }

}