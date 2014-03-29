package ch17

object ex06 {
  def middle[T](ible: Iterable[T]) = {
    ible.drop((ible.size - 1) / 2).head
  }

  def main(args: Array[String]) {
    val lst = List(1, 2, 3, 4, 5)
    println(middle(lst))
  }
}