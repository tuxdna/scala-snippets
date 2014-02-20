package dp

/*
 * Assembly Line from CLRS Ch15
 */
object assemblyLine {

  def main(args: Array[String]) = {
    val numLines = 2
    val a = List(List(7, 9, 3, 4, 8, 4),
      List(8, 5, 6, 4, 5, 7))
    val t = List(List(2, 3, 1, 3, 4),
      List(2, 1, 2, 2, 1))
    val e = List(2, 4)
    val x = List(3, 2)
    val n = 6
    val f = Array.ofDim[Int](2, n)
    val l = Array.ofDim[Int](2, n)

    // fastestWay
    f(0)(0) = e(0) + a(0)(0)
    f(1)(0) = e(1) + a(1)(0)
    for (j <- 1 until n) {
      val cost0same = f(0)(j - 1) + a(0)(j)
      val cost0switch = f(1)(j - 1) + t(1)(j - 1) + a(0)(j)
      if (cost0same <= cost0switch) {
        f(0)(j) = cost0same
        l(0)(j) = 0
      } else {
        f(0)(j) = cost0switch
        l(0)(j) = 1
      }

      val cost1same = f(1)(j - 1) + a(1)(j)
      val cost1switch = f(0)(j - 1) + t(0)(j - 1) + a(1)(j)
      if (cost1same <= cost1switch) {
        f(1)(j) = cost1same
        l(1)(j) = 1
      } else {
        f(1)(j) = cost1switch
        l(1)(j) = 0
      }
    }

    val fstar0 = f(0)(n - 1) + x(0)
    val fstar1 = f(1)(n - 1) + x(1)
    val fstar = Math.min(fstar0, fstar1)
    val lstar = if (fstar0 <= fstar1) 0 else 1

    println(e)
    println(a)
    println(t)
    println(x)
    println(n)
    println(f.map(_.toList).toList.mkString("\n"))
    println(fstar)
    println(l.map(_.toList).toList.mkString("\n"))
    println(lstar)

    // print path
    var i = lstar
    for (j <- (0 until n).reverse) {
      println("line %d, station %d".format(i + 1, j + 1))
      i = l(i)(j)
    }
  }
}
