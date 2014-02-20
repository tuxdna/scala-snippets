import scala.io.Source

object test extends App {
  for (line <- Source.fromFile(args(0)).getLines()) {
    val currentLine = line
    println(currentLine)
  }
  def condition = true
  if (condition) { val x = 2 } else { val x = 3 }
  val x = if (condition) { 2 } else { 3 }
}