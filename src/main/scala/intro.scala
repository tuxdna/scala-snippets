

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

  List(1 to 10) foreach println

  (1 to 5).toList foreach print; println

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

  // function as an argument
  def incfun(x: Int) = x + 1 // incfun: (x: Int)Int
  def applyon(x: Int, myfun: Int => Int) = myfun(x) // applyon: (x: Int, myfun: Int => Int)Int
  applyon(10, incfun) // res8: Int = 11
  def plus5(x: Int) = x + 5 // plus5: (x: Int)Int
  applyon(10, plus5) // res9: Int = 15

  // function as a return value
  def myfunc(x: Int): Int => Int = {
    def fun(y: Int) = y + x
    return fun
  } // myfunc: (x: Int)Int => Int
  val incby4 = myfunc(4) // incby4: Int => Int = <function1>
  println("increment 10 by 4: " + incby4(10)) // res10: Int = 14

  import scala.math._
  pow(3, Pi) // res15: Double = 31.54428070019754

  // apply method
  "Hello"(4) // res23: Char = o
  "Hello".apply(4) // res24: Char = o

  // object construction using apply
  // a common scala idiom to construct objects
  BigInt(10) // res26: scala.math.BigInt = 10
  BigInt.apply(10) // res27: scala.math.BigInt = 10
  val x = 20
  val sign = if (x > 0) +1 else -1
  println(sign)
  println(sign)

  // variable number of function arguments
  def sum(args: Int*) = {
    var result = 0
    for (arg <- args) result += arg
    result
  } // sum: (args: Int*)Int
  sum(1, 2, 3, 4) // res16: Int = 10
  // sum(1 to 10) //  error: type mismatch; found   : scala.collection.immutable.Range.Inclusive, required: Int
  sum(1 to 10: _*) // res20: Int = 55

  def sumRecursive(args: Int*): Int = {
    if (args.length == 0) 0
    else args.head + sumRecursive(args.tail: _*)
  }

  def recursiveSum(args: Int*): Int = {
    if (args.length == 0) 0
    else args.head + recursiveSum(args.tail: _*)
  }

  // exception handling
  // try {} catch {} finally {}
  import java.net.URL
  import java.io.InputStream
  import java.net.MalformedURLException
  import java.net.UnknownHostException
  import java.io.IOException
  import java.io.FileNotFoundException

  def trycatch = {
    def process(stream: InputStream) = {
      stream.read()
    }
    val url = new URL("http://horstmann.com/fred.gif")
    var stream: InputStream = null
    try {
      stream = url.openStream()
      process(stream)
    } catch {
      case ex1: MalformedURLException => println("Bad URL: " + url)
      case ex: UnknownHostException => println("host not found: " + ex.getMessage)
      case ex: FileNotFoundException => println("file not found: " + ex.getMessage)
      case ex2: IOException => println(ex2)
    } finally {
      stream.close()
    }
  }

  // trycatch

  val output = {
    val a = Array(1, 2, 3, 4, 5, 6, 7, 8)
    for (i <- a if i % 2 == 0) yield i * i
    for (i <- a if i % 2 != 0) yield i * i
  }

  for (i <- output) print(i + " "); println

  // maps in scala
  val m1 = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  val m2 = m1 + (4 -> "Four")
  val m3 = m1 + (1 -> "First", 5 -> "Fifth")
  println(m1)
  println(m2)
  println(m3)
  val m4 = m1 ++ m2 ++ m3
  for ((k, v) <- m4) println(k + ": " + v)
  val m5 = scala.collection.mutable.Map("Jan" -> 1, "Feb" -> 2)
  m5("Mar") = 3
  println(m5)
  println(m5.getOrElse("Apr", 0))
  m5 -= "Jan"
  println(m5)

  import scala.collection.immutable.SortedMap
  val m6 = SortedMap(1 -> "One", 2 -> "Two", 3 -> "Three")
  println(m6)

  val months = scala.collection.mutable.LinkedHashMap("January" -> 1,
    "February" -> 2, "March" -> 3, "April" -> 4, "May" -> 5)
  println(months)

  // classes take parameters in Scala
  class A(val args: String*) {
    def this(name: String, args: Int*) {
      this(name)
      for (i <- args) print(i + " "); println()
    }

    /*
     * uncomment this auxiliary constructor to 
     * get compile time error: ambiguous constructor
     * https://issues.scala-lang.org/browse/SI-2991
     
    def this(name: String, args: String*) {
      this(name + args)
      for (i <- args) print(i + " "); println()
    }
    
    
    * 
    */
  }

  val a1 = new A("hello", 1, 2, 3, 4)
  println(a1.args)

  // private  constructor in Scala
  class B private (val name: String) {
    def this(name: String, age: Int) = {
      this(name)
    }
  }

  val b1 = new B("somebody", 100)
  println(b1.name)

  // with companion object
  {
    class C(val name: String)

    object C {
      def create(name: String) = new C(name)
    }

    val c1 = C.create("some one")
    println(c1.name)
  }

  {
    klazz.klazz.myprint
    val p1 = new klazz.Person("You", 80)
    val p2 = new klazz.Employee(26)
    println(p1)
    println(p2)
  }
}

package klazz {

  // package object
  object klazz {
    def myprint = println("Hello")
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

  // inheritance
  class Employee(name: String, age: Int, var salary: Double) extends Person(name: String, age: Int) {
    def this(name: String) = this(name, 25, 0.0)
    def this(age: Int) = this("Me", age, 0.0)
    override def toString = name + " " + age + " - earns: " + salary
  }

}

