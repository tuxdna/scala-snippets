package ch18

object ex09 {
  abstract class Dim[T](val value: Double, val name: String) {
    self: T =>
    protected def create(v: Double): T
    def +(other: Dim[T]) = create(value + other.value)
    override def toString() = value + " " + name
  }

  class Seconds(v: Double) extends Dim[Seconds](v, "s") {
    protected def create(v: Double) = new Seconds(v)
  }

  //  class Meters(v: Double) extends Dim[Seconds](v, "m") {
  //    protected def create(v: Double) = new Seconds(v)
  //  }

  class Meters(v: Double) extends Dim[Meters](v, "m") {
    protected def create(v: Double) = new Meters(v)
  }

  def main(args: Array[String]): Unit = {
    println("Read the code")
  }

}
