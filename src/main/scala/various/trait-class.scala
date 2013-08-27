package various.traits

object NullWorld extends App {
  trait A {
    val foo: String
  }
  trait B extends A {
    val bar = foo + "World"
  }

  class C extends B {
    val foo = "Hello"
    println(bar)
  }

  val c = new C
  println(c.bar)
}
