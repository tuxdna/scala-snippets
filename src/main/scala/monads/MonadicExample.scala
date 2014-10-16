package monads

object MonadicExample {

  object SimpleCompute {
    class Foo { def bar = new Bar }
    class Bar { def baz = new Baz }
    class Baz { def compute = 0 }

    // first version
    def compute1(foo: Foo) = {
      if (foo != null) {
        val bar = foo.bar
        if (bar != null) {
          val baz = bar.baz
          if (baz != null) baz.compute else null
        } else null
      } else null
    }
  }

  object SomeMonadsHere {
    trait Monad[A] {
      def map[B](f: A => B): Monad[B]
      def flatMap[B](f: A => Monad[B]): Monad[B]
    }

    sealed trait Option[A] {
      def map[B](f: A => B): Option[B]
      def flatMap[B](f: A => Option[B]): Option[B]
    }
    case class Some[A](a: A) extends Option[A] {
      def map[B](f: A => B): Option[B] = new Some(f(a))
      def flatMap[B](f: A => Option[B]): Option[B] = f(a)
    }
    case class None[A] extends Option[A] {
      def map[B](f: A => B): Option[B] = new None
      def flatMap[B](f: A => Option[B]): Option[B] = new None
    }
  }

  object MonadicCompute {
    class Foo { def bar: Option[Bar] = Some(new Bar) }
    class Bar { def baz: Option[Baz] = Some(new Baz) }
    class Baz { def compute: Option[Int] = Some(0) }

    // second version
    def compute2(foo: Option[Foo]) = {
      foo.flatMap { f =>
        f.bar.flatMap { b =>
          b.baz.flatMap { b =>
            b.compute
          }
        }
      }
    }

    // fourth version
    def compute3(foo: Option[Foo]) = {
      foo.flatMap(_.bar.flatMap(_.baz.flatMap(_.compute)))
    }

    // fifth version
    def compute4(fooOpt: Option[Foo]) = {
      for { foo <- fooOpt; bar <- foo.bar; baz <- bar.baz } yield baz.compute
    }
  }

}