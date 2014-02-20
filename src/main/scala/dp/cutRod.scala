package dp

object cutRod {

  def cutRodNaive(p: Array[Int], n: Int): Int = {
    if (n == 0) 0
    else {
      var q = Int.MinValue
      for (i <- 0 to n - 1) {
        q = Math.max(q, p(i) + cutRodNaive(p, n - (i + 1)))
      }
      q
    }
  }

  def cutRodMemoized(p: Array[Int], n: Int, r: Array[Int]): Int = {
    if (r(n) > 0) r(n)
    else {
      var q = Int.MinValue
      if (n == 0) q = 0
      else {
        q = Int.MinValue
        for (i <- 0 to n - 1) {
          q = Math.max(q, p(i) + cutRodMemoized(p, n - (i + 1), r))
        }
      }
      r(n) = q
      q
    }
  }

  def cutRodBottomUp(p: Array[Int], n: Int): Int = {
    val r = Array.ofDim[Int](n + 1)
    r(0) = 0
    for (j <- 1 to n) {
      var q = Int.MinValue
      for (i <- 0 to j - 1) {
        q = Math.max(q, p(i) + r(j - i - 1))
      }
      r(j) = q
    }
    r(n)
  }

  def cutRod(p: Array[Int], n: Int) = {
    // method 1
    // cutRodNaive(p,n)

    // method 2
    // val r = Array.ofDim[Int](n + 1)
    // cutRodMemoized(p, n, r)

    // method 3
    cutRodBottomUp(p, n)
  }

  def cutRodBottomUpExtended(p: Array[Int], n: Int) = {
    val r = Array.ofDim[Int](n + 1)
    val s = Array.ofDim[Int](n + 1)
    r(0) = 0
    for (j <- 1 to n) {
      var q = Int.MinValue
      for (i <- 0 to j - 1) {
        val cost = p(i) + r(j - i - 1)
        if(q < cost) {
          s(j) = i
          q = cost
        }
      }
      r(j) = q
    }
    (r,s)
  }
  
  def printCutRodSolution(p: Array[Int], n: Int)  = {
    val (r,s) = cutRodBottomUpExtended(p, n)
    println(r.toList)
    println(s.toList)
    var n1 = n
    while(n1 > 0) {
      print(s(n) + 1)
      n1 = n1 - ( s(n) + 1 )
    }
  }
  
  def main(args: Array[String]): Unit = {
    val price = Array(1, 5, 8, 9, 10, 17, 17, 20, 24, 30, 35, 40, 44, 48, 53)
    // val price = (1 to 100000).toArray
    println(cutRod(price, 0))
    println(cutRod(price, 1))
    println(cutRod(price, 2))
    println(cutRod(price, 7))
    println(cutRod(price, 10))
    println(cutRod(price, price.length))
    
    printCutRodSolution(price, 8)
  }
}
