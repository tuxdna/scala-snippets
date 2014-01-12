package ch11

object ex05 extends App {

  class Table {
    var rows = List[List[String]](List[String]())
    def |(v: String) = {
      // add td to current row
      val currentRow = rows.last ++ List(v)
      rows = rows.take(rows.length - 1) ++ List(currentRow)
      this
    }
    def ||(v: String) = {
      val currentRow = List(v)
      rows = rows ++ List(currentRow)
      this
    }
    override def toString = {
      val rv = rows.map { r =>
        val x = r.map { c =>
          f"<td>$c</td>"
        }.mkString("")
        f"<tr>$x</tr>"
      }.mkString("")
      f"<table>$rv</table>"
    }
  }

  object Table {
    def apply() = new Table
  }

  val t1 = Table()
  val t2 = Table()
  println(t1 == t2)
  println(t1 | "Hello")
  println(Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET")
  
}