package ch16

object ex07 extends App {
  def toXML(m: Map[String, String]) = {
    <dl>{ m.map(e => <dt>{ e._1 }</dt><dd>{ e._2 }</dd>) }</dl>
  }

  val m = Map("A" -> "1", "B" -> "2")
  println(toXML(m))
}
