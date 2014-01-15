object fun extends App {
  class A

  trait SideKicker[T <: A] {
    def createSidekick: T
  }

  class B extends A

  object B extends SideKicker[B] {
    def createSidekick = {
      println("create B")
      new B
    }
  }

  class C extends A

  object C extends SideKicker[C] {
    def createSidekick = {
      println("create C")
      new C
    }
  }

  object foo {
    def bar[T <: A](f: SideKicker[T]) = {
      f.createSidekick
    }
  }

  val b = foo.bar(B)
  val c = foo.bar(C)
}
