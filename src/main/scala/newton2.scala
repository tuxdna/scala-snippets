object newton2 {
  def sqrt(x: Double) = { 
    def sqrtIter(guess: Double): Double = {
      if(isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))
    }
    
    def isGoodEnough(guess: Double) = math.abs(guess*guess - x) / x < 0.001
    def improve(guess: Double) = (guess + x / guess) / 2
    
    sqrtIter(1.0)
  }

  def main(args: Array[String]) {
    println(sqrt(2))
    println(sqrt(4))
    println(sqrt(1e-06))
    println(sqrt(1e60))
  }
}
