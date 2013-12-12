package ch16

object ex08 extends App {
  def toMap(x: xml.Elem) = {
    val dt = x \\ "dt"
    val dd = x \\ "dd"

    dt.zip(dd) map { a =>
      val (x, y) = a
      x.text -> y.text
    } toMap
  }
  val x = <dl><dt>A</dt><dd>1</dd><dt>B</dt><dd>2</dd></dl>

  println(toMap(x))
}
