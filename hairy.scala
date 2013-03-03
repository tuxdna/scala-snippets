
object hairy extends App {
  trait A {
    object stuff {
      object Foo
      object Bar
      object Baz
    }

  }

  trait B extends A {
    object moar {
      object Foo
      object Bar
      object Baz
    }
  }
}
