package various.matchheadtail

object test {

  val willWork = List(1, 3, 23, 90)
  val willNotWork = List(4, 18, 52)
  val empty = List()

  def processList(l: List[Any]): Unit = l match {
    case head :: tail =>
      "%s ".format(head)
      processList(tail)
    case Nil => println("")
  }

  for (l <- List(willWork, willNotWork, empty)) {
    print("List: ")
    processList(l)
  }

}