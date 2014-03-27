package ch14

object ex05 {

  def leafSum(l: Any): Int = {
    l match {
      case y: List[Any] => y.map(x => leafSum(x)).sum
      case z: Int => z
    }
  }

  def main(args: Array[String]): Unit = {
    val lst = List(List(3, 8), 2, List(5))
    println(leafSum(lst))
  }
}