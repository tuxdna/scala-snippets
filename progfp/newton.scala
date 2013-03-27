object newton {
  def sqrt(x: Double) = { 

    def sqrtIter(guess: Double, x: Double): Double = {
      if(isGoodEnough(guess, x)) guess
      else sqrtIter(improve(guess, x), x)
    }
    
    def isGoodEnough(guess: Double, x: Double) = math.abs(guess*guess - x) / x < 0.001
    def improve(guess: Double, x:Double) = (guess + x / guess) / 2
    
    sqrtIter(1.0, x)
  }

  def main(args: Array[String]) {
    println(sqrt(2))
    println(sqrt(4))
    println(sqrt(1e-06))
    println(sqrt(1e60))
  }
}
