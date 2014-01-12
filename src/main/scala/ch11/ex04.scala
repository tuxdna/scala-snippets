package ch11

object ex04 extends App {
  class Money(d: Int, c: Int) {
    val dollar = d + c / 100
    val cent = c % 100

    override def toString = f"Money($dollar, $cent)"

    def +(that: Money) = {
      Money(this.dollar + that.dollar, this.cent + that.cent)
    }
    def -(that: Money) = {
      //TODO: Fix substraction
      Money(this.dollar - that.dollar, this.cent - that.cent)
    }
  }
  object Money {
    def apply(d: Int, c: Int) = new Money(d, c)
  }

  val m1 = Money(1, 75)
  val m2 = Money(0, 50)

  println(m1)
  println(m2)
  println(m1 + m2)
  println(m1 - m2)
  println(m2 - m1)
}
