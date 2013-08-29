object intro extends App {

  println("Welcome to the Scala")
  def add(a: Int, b: Int): Int = {
    a + b
  }

  def max(x: Int, y: Int): Int = {
    if (x > y) x else y
  }

  def justGreet(name: String) = println("Hello " + name)

  val number = {
    val x: Int = 7
    val y: Int = 9
    x + y
  }

  List(1, 2, 3).foreach(l => println(l))
  List(1, 2, 3) foreach println
  (1 to 5).toList foreach println
  List(1 to 100) foreach println

  // Mutability
  val gs = new Array[String](3)
  gs(0) = "Bank"
  gs(1) = "of"
  gs(2) = "America"
  val a = (1, "ten")

  // Pattern Matching

  class Calculator(brand: String, model: String)

  val c1 = new Calculator("HP", "20B")
  val c2 = new Calculator("HP", "90Sci")
  c1 == c2

  val c3 = new Calculator("HP", "20B")
  c1 == c3

  var i = 10
  val j = while (i > 0) i = i - 1

  // for expression
  val items = for { i <- 1 to 10 } yield i

  object SingletonObject {
    val bar = "Hello"
  }

  class User {
    SingletonObject.bar
  }

  class Person(name: String, val age: Int) {
    require(age >= 25)
    def this(name: String) = this(name, 25)
    def this(age: Int) = this("Me", age)
    override def toString = name + " " + age
    def +(surname: String) = {
      name + " " + surname
    }
  }

  val p1 = new Person("tuxdna", 28)
  val p2 = new Person("some")

  // try catch throw
  try {
    val p3 = new Person(22)
    val k = 123.5 / 0.0
    throw new NullPointerException
  } catch {
    case e: IllegalArgumentException => println("requirement test suceeded")
    case _ => println("Some weird error happened")
  }

  p2 + "Too"
  p1.+("from India")

  p2.age
}
