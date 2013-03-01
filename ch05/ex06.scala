// 6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,”
// on page 51, provide a primary constructor that turns negative ages to 0.

object ex06 extends App {
  class Person(a: Int) {
    private var privateAge = if (a < 0) 0 else a // Make private and rename
    def age = privateAge
    def age_=(newValue: Int) {
      if (newValue > privateAge) privateAge = newValue; // Can’t get younger
    }
  }

  val fred = new Person(-1)
  println(fred.age) // 0
  fred.age = 21
  println(fred.age) // 21
  fred.age = 30
  println(fred.age) // 30
  fred.age = 21
  println(fred.age) // still 30 i.e. cant get any younger
}
