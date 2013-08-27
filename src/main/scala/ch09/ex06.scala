package ch09

/*
 * 6. Make a regular expression searching for quoted strings "like
 * this, maybe with \" or \\" in a Java or C++ program. Write a Scala
 * program that prints out all such strings in a source file.
 */

//TODO: Incomplete

import scala.io.Source
object ex06 extends App {
  var fileName = "numbers.txt"
  if (args.length < 1) {
    println("No input file. Exiting.")
    sys.exit(-1)
  }
  fileName = args(0)
  val source = Source.fromFile(fileName, "UTF-8")
  val commentPattern = ".*\"(.*(\\\").*)*\"".r
  val text = source.mkString
  source.close

  for(m <- commentPattern.findAllIn(text)) println(m)
}
