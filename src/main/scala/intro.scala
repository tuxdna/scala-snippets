object intro extends App {

  println("Welcome to Scala")
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
    println(SingletonObject.bar)
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
    case _: Throwable => println("Some weird error happened")
  }

  p2 + "Too"
  p1.+("from India")

  println(p2.age)



  def incfun(x: Int) = x + 1 // incfun: (x: Int)Int
  def applyon(x: Int, myfun: Int => Int) = myfun(x) // applyon: (x: Int, myfun: Int => Int)Int
  applyon(10, incfun) // res8: Int = 11
  def plus5(x: Int) = x + 5 // plus5: (x: Int)Int
  applyon(10, plus5) // res9: Int = 15

  def myfunc(x: Int): Int => Int = {
    def f(y: Int) = y + x
    f
  }
  // myfunc: (x: Int)Int => Int

  val incby4 = myfunc(4) // incby4: Int => Int = <function1>
  incby4(10) // res10: Int = 14

  import scala.math._
  pow(3,Pi) // res15: Double = 31.54428070019754

  // apply method
  "Hello"(4) // res23: Char = o
  "Hello".apply(4) // res24: Char = o

  // object construction using apply
  // a common scala idiom to construct objects
  BigInt(10) // res26: scala.math.BigInt = 10
  BigInt.apply(10) // res27: scala.math.BigInt = 10
  val x = 20
  val sign = if(x > 0) +1 else -1
  println(sign)
  println(sign)
  
}
