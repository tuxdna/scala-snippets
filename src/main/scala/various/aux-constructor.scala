package various.aux

class Person {
  println("0-constructor")
  private var name = ""
  private var age = 0
  def this(name: String) {
    this()
    println("1-constructor")
    this.name = name
  }

  def this(name: String, age: Int) {
    this(name)
    println("2-constructor")
    this.age = age
  }

  override def toString = {
    "[%s, %s]".format(name, age)
  }
}

object ex1 {
  println("p1:")
  val p1 = new Person()
  println("p2:")
  val p2 = new Person("Some")
  println("p3:")
  val p3 = new Person("Some", 1)

  println(p1, p2, p3)
}

import scala.collection.mutable.ArrayBuffer
class Network {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }
  private val members = new ArrayBuffer[Member]
  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

object ex2 {
  val chatter = new Network
  val myFace = new Network

  val fred = chatter.join("Fred")
  val wilma = chatter.join("Wilma")
  fred.contacts += wilma // OK
  val barney = myFace.join("Barney") // Has type myFace.Member
  
  // UNCOMMENT THIS LINE
  // fred.contacts += barney
  
  // No—can’t add a myFace.Member to a buffer of chatter.Member elements
}