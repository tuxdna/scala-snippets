/*
1. Write a Scala code snippet that reverses the lines in a file (making the last
line the first one, and so on).
*/

import scala.io.Source
object ex01 extends App {
  var source: Source = null
  if (args.length < 1) {
    println("No input from the command line. Now reading from stdin.")
    source = Source.stdin
  } else {
    source = Source.fromFile(args(0), "UTF-8")
  }
  for(l <- source.getLines.toArray.reverse) println(l)
}
