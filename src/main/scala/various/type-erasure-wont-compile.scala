package various.typeerasure

object Foo {
  def bar(list: List[String]) = list.toString

  // uncomment following line wont compile
  
  //def bar(list: List[Int]) = list.size.toString
}

