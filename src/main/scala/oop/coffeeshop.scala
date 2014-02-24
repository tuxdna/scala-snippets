package oop

object coffeeshop {
  class Coffee(val price: Double) {
    def this() = this(11.5)
    override def toString() = "Coffee(%.2f)".format(price)
  }

  case class CreditCard(name: String)

  class Payments { def charge(cc: CreditCard, price: Double) {} }

  case class Charge(cc: CreditCard, amount: Double) {
    def combine(other: Charge) = {
      if (cc == other.cc) {
        Charge(cc, amount + other.amount)
      } else throw new Exception("Can't combine amount from different cards")
    }
  }

  class Cafe {
    def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
      val cup = new Coffee()
      p.charge(cc, cup.price)
      cup
    }

    def buyCoffee(cc: CreditCard) = {
      val cup = new Coffee()
      (cup, Charge(cc, cup.price))
    }

    def buyCoffees(cc: CreditCard, n: Int) = {
      val purchases = List.fill(n)(buyCoffee(cc))
      val (coffees, charges) = purchases.unzip
      (coffees, charges.reduce((a, b) => a.combine(b)))
    }
  }

  def coalesce(charges: List[Charge]) = {
    charges.groupBy(_.cc).map(e => (e._1, e._2.reduce((a, b) => a.combine(b))))
  }

  def main(args: Array[String]) {
    val cc = CreditCard("Indo-Bank")
    val p = new Payments
    val coffeeShop = new Cafe
    val cup = coffeeShop.buyCoffee(cc, p)
    println(cup)
    val cups = coffeeShop.buyCoffees(cc, 10)
    println(cups)
  }
}
