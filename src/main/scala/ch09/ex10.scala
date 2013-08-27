package ch09

/*
 * 10. Expand the example with the serializable Person class that stores a
 * collection of friends. Construct a few Person objects, make some of
 * them friends of another, and then save an Array[Person] to a file. Read
 * the array back in and verify that the friend relations are intact.
*/

object ex10 extends App {
  import java.io._
  import scala.collection.mutable.ArrayBuffer

  class Person(val name: String) extends Serializable {
    val friends = new ArrayBuffer[Person]
    // OK - ArrayBuffer is serializable
    def description = name + " with friends " +
      friends.map(_.name).mkString(", ")
  }

  val fred = new Person("Fred")
  val wilma = new Person("Wilma")
  val barney = new Person("Barney")
  fred.friends += wilma
  fred.friends += barney
  wilma.friends += barney
  barney.friends += fred

  val out = new ObjectOutputStream(new FileOutputStream("test.obj"))
  out.writeObject(fred)
  out.close()
  val in = new ObjectInputStream(new FileInputStream("test.obj"))
  val savedFred = in.readObject().asInstanceOf[Person]
  in.close()

  println(savedFred.description)
  println(savedFred.friends.map(_.description))

}
