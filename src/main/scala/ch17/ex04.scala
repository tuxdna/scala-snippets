package ch17

object ex04 {
  class Person
  class Student extends Person

  class Pair[T](val first: T, val second: T) {
    // def replaceFirst(newFirst: T) = new Pair[T](newFirst, second)
    def replaceFirst[R >: T](newFirst: R) = new Pair(newFirst, second)
  }

  def main(args: Array[String]) {
    val p = new Person
    val s1, s2 = new Student

    val pair1 = new Pair(s1, s2)

    pair1.replaceFirst(p)
  }

}