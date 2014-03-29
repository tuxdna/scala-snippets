package ch17

object ex02 {

  class Pair[T](var t: T, var s: T) {
    def swap() = {
      val temp = s
      s = t
      t = temp
    }
    override def toString = {
      "Pair(%s, %s)".format(t,s)
    }
  }

  def main(args: Array[String]) {
    val p1 = new Pair("hi", "hello")
    println(p1)
    p1.swap()
    println(p1)
  }
}
