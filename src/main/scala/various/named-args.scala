package various.namedargs

object test {

  def decorate(str: String, left: String = "[", right: String = "]") =
    left + str + right

  println(decorate("Hello"))
  println(decorate("Hello", "<<<", ">>>"))
  println(decorate("Hello", "<<<", right = ">}>"))
  println(decorate(right = "}", left = "{", str = "Hello"))

}