//comment
object Upper {
  def upper(strings: String*): Seq[String] = strings.map(_.toUpperCase())
}

Console.println(Upper.upper("A", "First", "Scala", "Program"))
