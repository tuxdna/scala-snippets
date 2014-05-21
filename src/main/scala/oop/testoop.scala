package oop

object testoop {

  case class User(id: Option[Long], username: String, password: String, role: Option[String])
  // adding a companion object to a case class, makes it lose tupled method
  // https://groups.google.com/forum/#!topic/scala-user/jyWBMz5Qslw
  object User {
    def apply(id: Long, username: String, password: String, role: String): User = User(Some(id), username, password, Some(role))
    def dd(tuple: Tuple4[Option[Long], String, String, Option[String]]) = {
      // id: Option[Long], username: String, password: String, role: Option[String]
      User(tuple._1, tuple._2, tuple._3, tuple._4)
    }
    val tupled = dd(_)
  }

  def main(args: Array[String]) {
    val persons = Array(
      new Person(2, "amar"),
      new Person(3, "akbar"),
      new Person(1, "anthony"),
      new Person())

    println(persons mkString (", "))
    println(persons.sortBy(f => f.age) mkString (", "))
    println(persons.sortBy(f => f.name) mkString (", "))

    val tupledFunc = User.tupled

  }
}