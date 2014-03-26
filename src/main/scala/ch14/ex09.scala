package ch14

object ex09 {
  def sum(lst: List[Option[Int]]) = {
    lst.filterNot(_ == None).map(_.get).sum
  }
  
  def main(args: Array[String]): Unit = {
    val lst = List[Option[Int]](Some(1), None, Some(2), None)
    println(sum(lst))
  }
}