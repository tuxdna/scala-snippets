package ch17

object ex03 {
  class Pair {
    def swap[T, S](pair: Tuple2[T, S]) = (pair._2, pair._1)
  }

  def main(args: Array[String]) {
    val p1 = new Pair
    println(p1)
    val p2 = p1.swap((1, "hello"))
    println(p2)
  }
}