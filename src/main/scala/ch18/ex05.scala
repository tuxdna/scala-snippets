package ch18
import scala.collection.mutable.ArrayBuffer

object ex05 {
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

  def process1[M <: n.Member forSome { val n: Network }](m1: M, m2: M) = (m1, m2)

  type NetworkMember = n.Member forSome { val n: Network }

  def process2(m1: NetworkMember, m2: NetworkMember) = (m1, m2)

  def main(args: Array[String]): Unit = {

    val chatter = new Network
    val myFace = new Network

    val fred = chatter.join("Fred")
    val wilma = chatter.join("Wilma")
    val barney = myFace.join("Barney")
    fred.contacts += barney

    println(chatter)
    println(myFace)
    println("fred: " + fred)
    println

    process1(fred, wilma)
    // No type projection
    // process1(fred, barney) 

    process2(fred, wilma)
    // type projection
    process2(fred, barney)

  }

}