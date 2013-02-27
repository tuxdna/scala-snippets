

object varargs {
  def main(args: Array[String]) {
    val msg = java.text.MessageFormat.format(
      "At {1,time} on {1,date}, there was {2} on planet {0}.",
      "Hoth", new java.util.Date(), " it happened.")
    println("Message="+msg)
  }
  def sum(args: Int*) = {
    var result = 0
    for (arg <- args) result += arg
    result
  }
}

varargs.main(Array())
println(varargs.sum(1,2,3,4,5,6))

def sum(args: Int*): Int = {
  if(args.length == 0) 0
  else args.head + sum(args.tail : _*)
}

println(sum(1 to 6: _*))

def box(s : String) { // Look carefully: no =
  val border = "-" * s.length + "--\n"
  println(border + "|" + s + "|\n" + border)
}

box("Hello Scala World!")

