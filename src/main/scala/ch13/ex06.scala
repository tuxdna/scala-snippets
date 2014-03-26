package ch13

object ex06 {
  def main(args: Array[String]) {
    val lst = List(1, 2, 3)
    val x = (lst :\ List[Int]())(_ :: _)
    val y = (List[Int]() /: lst)(_ :+ _)
    println(x)
    println(y)

    val p = (lst :\ List[Int]())((a, b) => b :+ a)
    val q = (List[Int]() /: lst)((a, b) => b :: a)
    println(p)
    println(q)
  }
}