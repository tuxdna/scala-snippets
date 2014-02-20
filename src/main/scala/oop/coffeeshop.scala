package oop

object coffeeshop {
  class Coffee(val price: Int) { def this() = this(10) }
  class CreditCard
  class Payments { def charge(cc: CreditCard, price: Int) {} }

  class Cafe {
    def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
      val cup = new Coffee()
      p.charge(cc, cup.price)
      cup
    }
  }
  def main(args: Array[String]) {
    val cc = new CreditCard
    val p = new Payments
    val coffeeShop = new Cafe
    val cup = coffeeShop.buyCoffee(cc, p)
  }
}
