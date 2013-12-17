package various

object tojson extends App {
def toJson(a: Any): String = {
    a match {
      // number
      case m: Number => m.toString
      
      // string
      case m: String => "\"" + m + "\""
      
      // map
      case m: Map[_, _] => {
        "{" + (m map { x => val key = x._1; toJson(key) + ": " + toJson(m(key)) } mkString (", ")) + "}"
      }

      // list
      case l: Seq[_] => { "[" + (l map (toJson(_)) mkString (",")) + "]" }

      // for anything else: tuple
      case m: Product => toJson(m.productIterator.toList)
      case m: AnyRef => "\"" + m.toString + "\""
    }
  }

  class Person(val name: String, val country: String) {
    override def toString = s"Person(Name: $name, Country: $country)"
  }
  val s = "Hello"
  val i = 120
  val l = List(1, 2, 3)
  val m = Map("A" -> "230", "B" -> 2)
  val ml = Map("A" -> l, "B" -> l)
  val tuple = (i, l, m, ml, new Person("Bond", "Italy"))

  println(toJson(s))
  println(toJson(i))
  println(toJson(l))
  println(toJson(m))
  println(toJson(ml))
  println(toJson(tuple))
}