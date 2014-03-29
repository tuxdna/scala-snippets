package ch17

object ex01 {

  case class Pair[T, S](t: T, s: S) {
    def swap() = Pair(s, t)
  }
  
  def main(args: Array[String]) {
  val p1 = Pair(1,"hello")
  println(p1)
  val p2 = p1.swap()
  println(p2)
}

}