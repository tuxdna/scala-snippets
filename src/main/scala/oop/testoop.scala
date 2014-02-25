package oop

object testoop {
  def main(args: Array[String]) {
    val persons = Array(
      new Person(2, "amar"),
      new Person(3, "akbar"),
      new Person(1, "anthony"),
      new Person())

    println(persons mkString (", "))
    println(persons.sortBy(f => f.age) mkString (", "))
    println(persons.sortBy(f => f.name) mkString (", "))
  }
}