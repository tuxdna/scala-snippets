package various.traitinvalid

object test {

  trait AbstractT2 {
    println("In AbstractT2:")
    val value: Int
    val inverse = 1.0 / value // ???
    println("AbstractT2: value = " + value + ", inverse = " + inverse)
  }

  val c2b = new AbstractT2 {
    val value = 10
    println("In c2b:")
  }

  println("c2b.value = " + c2b.value + ", inverse = " + c2b.inverse)

  val c2c = new {
    val value = 10
  } with AbstractT2

  println("c2c.value = " + c2c.value + ", inverse = " + c2c.inverse)

}
