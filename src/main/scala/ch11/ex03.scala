package ch11

object ex03 extends App {
  class Fraction(n: Int, d: Int) {
    private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
    private val den: Int = if (d == 0) 1 else d * sign(d) / gcd(n, d)
    override def toString = num + "/" + den
    def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
    def gcd(a: Int, b: Int): Int = if (b == 0) math.abs(a) else gcd(b, a % b)
    def +(that: Fraction): Fraction = {
      val a = this
      val b = that
      Fraction(a.num * b.den + a.den * b.num, a.den * b.den)
    }
    def -(that: Fraction): Fraction = {
      val a = this
      val b = that
      Fraction(a.num * b.den - a.den * b.num, a.den * b.den)
    }
    def *(that: Fraction): Fraction = {
      val a = this
      val b = that
      Fraction(a.num * b.num, a.den * b.den)
    }

    def /(that: Fraction): Fraction = {
      val a = this
      val b = that
      Fraction(a.num * b.den, a.den * b.num)
    }
  }

  object Fraction {
    def apply(n: Int, d: Int) = new Fraction(n, d)
  }

  val f1 = Fraction(12, 9)
  val f2 = Fraction(-11, 9)
  println(f1)
  println(f2)
  println(f1 + f2)
  println(f1 - f2)
  println(f1 * f2)
  println(f1 / f2)
}
