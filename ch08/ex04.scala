/*
 * 4. Define an abstract class Item with methods price and description.
 * A SimpleItem is an item whose price and description are specified
 * in the constructor. Take advantage of the fact that a val can
 * override a def. A Bundle is an item that contains other items.
 * Its price is the sum of the prices in the bundle. Also provide a
 * mechanism for adding items to the bundle and a suitable description
 * method.
 */

object ex04 extends App {

  abstract class Item {
    def price: Int
    def description: String
  }

  class SimpleItem(val price: Int,
		   val description: String) extends Item {
  }

  import scala.collection.mutable.ArrayBuffer

  class Bundle extends Item {
    val items = ArrayBuffer[Item]()
    def add(item: Item) = {items += item}
    override def price = {
      var sum = 0
      for(i <- items) { sum += i.price }
      sum
    }
    override def description: String = {
      val v = for(i <- items) yield (i.description, i.price)
      v.mkString(",")
    }
  }

  val b = new Bundle
  val i1 = new SimpleItem(10, "A")
  val i2 = new SimpleItem(12, "B")
  b.add(i1)
  b.add(i2)
  println(b.price)
  println(b.description)
}
