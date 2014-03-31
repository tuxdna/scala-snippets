package ch21

object ex02 {

  class RichNum(val x: Int) {
    def +%(y: Int) = x + (x * y / 100)
  }

  implicit def int2RichNum(from: Int) = new RichNum(from)

  def main(args: Array[String]): Unit = {
    val z = 120 +% 10
    println(z)
  }
}
