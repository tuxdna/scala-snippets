package ch11

object ex07 extends App {

  class BitSequence(v: Long) {
    var value = v
    def update(pos: Int, b: Boolean) = {
      val mask = 1l << pos
      value = if (b) value | mask else value & ~mask
    }

    def apply(pos: Int): Boolean = {
      val mask = 1l << pos
      (value & mask) != 0
    }

    override def toString = {
      java.lang.Long.toBinaryString(value)
    }
  }

  object BitSequence {
    def apply(n: Long) = {
      new BitSequence(n)
    }
  }

  val s0 = BitSequence(0xff00ff0000ffaa1bL)

  println(s0)
  s0(0) = false
  println(s0)
  println(s0(0))
  println(s0(1))
  println(s0(2))

}
