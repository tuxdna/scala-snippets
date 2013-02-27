// exponent function
def xpowern(x: Double, n: Int): Double = {
  if( n < 0) {
    1 / xpowern(x, -n)
  }
  else if( n == 0) {
    1
  } else if (n%2==0) {
    // compute y*y where y = x power n/2
    val k = xpowern(x, n/2)
    k * k
  } else {
    x*xpowern(x, n-1)
  }
}
