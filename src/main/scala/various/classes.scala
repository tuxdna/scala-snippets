package various.classes

class Person {
  private var privateAge = 0 // Make private and rename
  def age = privateAge
  def age_=(newValue: Int) {
    if (newValue > privateAge) privateAge = newValue; // Can’t get younger
  }
}

object ex4 {
  val fred = new Person
  fred.age = 30
  fred.age = 21
  println(fred.age) // 30
}
class Counter {
  private var value = 0 // You must initialize the field
  val value2 = 12
  def increment() { value += 1 } // Methods are public by default
  def current = value

  def isLess(other: Counter) = value < other.value
}

object ex3 {
  val myCounter = new Counter // Or new Counter()
  myCounter.increment()
  println(myCounter.current)
  println(myCounter.value2)

  val oCounter = new Counter
  println(myCounter.isLess(oCounter))

}

import scala.reflect.BeanProperty

class Person1 {
  @BeanProperty var name: String = _
}
