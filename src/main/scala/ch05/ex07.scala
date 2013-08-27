// 7. Write a class Person with a primary constructor that accepts a string containing
// a first name, a space, and a last name, such as new Person("Fred Smith"). Supply
// read-only properties firstName and lastName. Should the primary constructor
// parameter be a var, a val, or a plain parameter? Why?

object ex07 extends App {
  class Person(name: String) {
    val _names = if (
             name.split(" ").length == 2
          ) name.split(" ") else Array(name.split(" ")(0), "")
    def firstName = _names(0)
    def lastName = _names(1)
  }

  val p1 = new Person("James Bond")
  println( "%s, %s".format(p1.firstName, p1.lastName) )
  val p2 = new Person("James ")
  println( "%s, %s".format(p2.firstName, p2.lastName) )
  val p3 = new Person(" Bond")
  println( "%s, %s".format(p3.firstName, p3.lastName) )
  val p4 = new Person("JamesBond")
  println( "%s, %s".format(p4.firstName, p4.lastName) )
}
