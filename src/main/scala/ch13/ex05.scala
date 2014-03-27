package ch13

object ex05 {
  def myMkString[T](s: Seq[T], sep: String) = {
    s.view.map(_.toString) reduceLeft ((x, y) => x + sep + y)
  }

  def main(args: Array[String]) {
    val ls1 = List("A", "B", "C")
    println(myMkString(ls1, ", "))
    val ls2 = List(1, 2, 3)
    println(myMkString(ls2, ", "))

  }

}