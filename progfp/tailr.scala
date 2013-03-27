object tailr {
  def gcd(a: Int, b: Int): Int = if(b==0) a else gcd(b, a % b)
  def factorial(n: Int) : Int = if(n==0) 1 else n * factorial(n-1)
  def factorialTr(n: Int) : Int = {
    def loop(acc: Int, n: Int): Int = {
      if(n==0) acc
      else loop(acc*n, n-1)
    }
    loop(1,n)
  }

  def main(args: Array[String]) {
    println(gcd(213, 124))
    println(factorial(4))
    println(factorialTr(4))
  }

}
