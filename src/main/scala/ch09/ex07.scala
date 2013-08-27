package ch09

/*
 * 7. Write a Scala program that reads a text file and prints all
 * tokens in the file that are not floating-point numbers. Use a
 * regular expression.
 */

import scala.io.Source
object ex07 extends App {
  var fileName = "numbers.txt"
  if (args.length < 1) {
    println("No input from the command line. Exiting.")
    sys.exit(-1)
  }
  fileName = args(0)
  val source = Source.fromFile(fileName, "UTF-8")
  val fpRegex = """(\d*\.\d+)""".r
  for(t <- source.mkString.split("\\s+")) {
    if ( fpRegex.findFirstIn(t) == None ) println(t)
  }
}
