package ch14

object ex03 {
  def swap(a: Array[Int]) = {
    a match {
      case Array(x, y, z @ _*) => Array(y, x) ++ z
      case _ => a
    }
  }
  def main(args: Array[String]): Unit = {
    val arr1 = Array(1)
    val arr2 = Array(1, 2, 3, 4)
    println(swap(arr1).toList)
    println(swap(arr2).toList)
  }
}
