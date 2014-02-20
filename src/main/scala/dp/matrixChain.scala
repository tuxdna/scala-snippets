package dp

object matrixChain {
  case class Matrix(ROWS: Int, COLS: Int)
  // val dim = List(5, 10, 7, 21, 5, 50)
  val dim = List(30, 35, 15, 5, 10, 20, 25)
  val mats = dim.sliding(2).map(x => Matrix(x(0), x(1))).toList
  
  def parens(s: Array[Array[Int]], i: Int, j: Int): String = {
    if (i == j) "A%d".format(i)
    else {
      val k = s(i)(j)
      "(" + parens(s, i, k) + " * " + parens(s, k + 1, j) + ")"
    }
  }

  def main(args: Array[String]) {
    println(mats.toList)
    val n = mats.length
    val m = Array.ofDim[Int](n, n)
    val s = Array.ofDim[Int](n, n)
    for (chainLength <- 2 to n) {
      for (i <- 0 until (n - chainLength + 1)) {
        val j = i + chainLength - 1
        m(i)(j) = Int.MaxValue
        println("cl: %d, i: %d, j: %d".format(chainLength, i, j))
        for (k <- i to j - 1) {
          val cost = m(i)(k) + m(k + 1)(j) +
            dim(i) * dim(k + 1) * dim(j + 1)
          if (cost < m(i)(j)) {
            m(i)(j) = cost
            s(i)(j) = k
          }
        }
      }
    }
    println("m = ")
    println(m.map(_.map("%5d".format(_)).toList).toList.mkString("\n"))
    println("s = ")
    println(s.map(_.map("%5d".format(_))toList).toList.mkString("\n"))
    println("optimal solution: " + parens(s, 0, n - 1))
  }
}
