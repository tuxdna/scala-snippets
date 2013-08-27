package various.functions

object test {
  // factorial
  def fac(n: Int) = {
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  }

  println("fac(10): " + fac(10))

  // absolute value
  def abs(x: Double) = if (x >= 0) x else -x
  println("abs(): " + abs(-fac(10)))

  // sign of a number -1,0,1
  def signum(num: Int) = {
    if (num > 0) 1
    else if (num < 0) -1
    else 0
  }

  println("signum()")
  println(signum(123))
  println(signum(-123))
  println(signum(0))

  // countdonw loop
  def countdown(n: Int) {
    for (i <- 0 to n; v = n - i) println(v)
  }
  println("countdonw(10)")
  countdown(10)

  // product using a loop
  var s = "Hello";
  def uniprod(s: String) = {
    var prod = 1;
    for (i <- 0 until s.length) prod *= s(i)
    prod
  }
  println("Product for %s : %s".format(s, uniprod(s)))

  // product using foldLeft
  println("Product for %s : %s".format(s, s.foldLeft(1)((a, b) => a * b)))

  // recursive product
  def prod_recursive(s: String, i: Int): Int = {
    if (i >= s.length) 1
    else s(i) * prod_recursive(s, i + 1)
  }
  println("Product for %s : %s".format(s, prod_recursive(s, 0)))

  // exponent function
  def xpowern(x: Double, n: Int): Double = {
    if (n < 0) {
      1 / xpowern(x, -n)
    } else if (n == 0) {
      1
    } else if (n % 2 == 0) {
      // compute y*y where y = x power n/2
      val k = xpowern(x, n / 2)
      k * k
    } else {
      x * xpowern(x, n - 1)
    }
  }

  println(xpowern(2, -2))
  println(xpowern(2, -3))
  println(xpowern(2, -4))
  println(xpowern(2, 0))
  println(xpowern(2, 2))
  println(xpowern(2, 3))
  println(xpowern(2, 4))

}