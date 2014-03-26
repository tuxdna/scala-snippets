package ch13

object ex04 {
  def mapValues(c: Seq[String], m: Map[String, Int]) = c flatMap (m.get(_))
  def main(args: Array[String]) {
    val c = Array("Tom", "Fred", "Harry")
    val m = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
    println(mapValues(c, m))
  }
}
