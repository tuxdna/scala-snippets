package ch21
import scala.math._

object ex05 {

  class Fraction(n: Int, d: Int) {
    val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
    val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);
    override def toString = num + "/" + den
    def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
    def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

    def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
  }

  class OrderedFraction(n: Int, d: Int) extends Fraction(n, d) with Ordered[Fraction] {
    def compare(that: Fraction): Int = {
      // Given fractions: a/b  and   c/d
      // Make bases same: ad / bd  and  bc / bd
      // Compare the numerators
      val (a, b) = (this.num, this.den)
      val (c, d) = (that.num, that.den)
      a * d - b * c
    }
  }

  object Fraction {
    def apply(n: Int, d: Int) = new Fraction(n, d)
  }

  def main(args: Array[String]) {
    implicit def int2Fraction(n: Int) = Fraction(n, 1)

    def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if (order(a) < b) a else b

    implicit def fraction2OrderedFraction(f: Fraction): OrderedFraction = {
      new OrderedFraction(f.num, f.den)
    }

    val result = 3 * Fraction(4, 5) // Calls int2Fraction(3)
    println(result)
    println(smaller(Fraction(1, 7), Fraction(2, 9)))
  }
}