package ch18
import scala.collection.mutable.ArrayBuffer

object ex04 {

  class Network {
    class Member(val name: String) {
      val contacts = new ArrayBuffer[Network#Member]

      override def toString = {
        "Member(name: %s, contacts: %s)".format(name, contacts mkString ", ")
      }

      override def equals(obj: Any): Boolean = {
        obj match {
          case x: Member => (name equals x.name) && (contacts equals x.contacts)
          case _ => false
        }
      }
    }

    private val members = new ArrayBuffer[Member]

    def join(name: String) = {
      val m = new Member(name)
      members += m
      m
    }

    override def toString = {
      "Network(%s)".format(members mkString ", ")
    }
  }

  def main(args: Array[String]): Unit = {

    val chatter = new Network
    val myFace = new Network

    val fred = chatter.join("Fred")
    val barney = myFace.join("Barney")
    fred.contacts += barney

    println(chatter)
    println(myFace)
    println("fred: " + fred)
    println

    // add another Fred
    val fred2 = chatter.join("Fred")
    fred2.contacts += barney
    println(chatter)
    println("fred2: " + fred2)
    println("fred equals fred2: " + (fred == fred2))
    println

    // add another Fred in other network
    val fred3 = myFace.join("Fred")
    fred3.contacts += barney
    println(myFace)
    println("fred3: " + fred3)
    println("fred equals fred3: " + (fred == fred3))

  }
}