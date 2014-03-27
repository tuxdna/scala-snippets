package ch14

object ex04 {

  abstract class Item
  case class Article(description: String, price: Double) extends Item
  case class Bundle(description: String, discount: Double, items: Item*) extends Item
  case class Multiple(m: Int, a: Item) extends Item

  def price(it: Item): Double = it match {
    case Article(_, p) => p
    case Bundle(_, disc, its @ _*) => its.map(price _).sum - disc
    case Multiple(m, a) => m * price(a)
  }

  def main(args: Array[String]): Unit = {
    val b = Bundle("Father's day special", 20.0,
      Article("Scala for the Impatient", 39.95),
      Bundle("Anchor Distillery Sampler", 10.0,
        Article("Old Potrero Straight Rye Whiskey", 79.95),
        Article("Junipero Gin", 32.95)),
      Multiple(10, Article("Stapler", 9.4)),
      Multiple(10, Article("Stapler", 10)))

    println(price(b))

  }

}