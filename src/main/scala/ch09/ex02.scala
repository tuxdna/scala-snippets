/*
2. Write a Scala program that reads a file with tabs, replaces each tab with spaces
so that tab stops are at n-column boundaries, and writes the result to the
same file.

*/

import scala.io.Source

object ex02 extends App {
  var fileName = ""
  if (args.length < 1) {
    println("No input from the command line. Exiting.")
    sys.exit(-1)
  }
  fileName = args(0)
  var source = Source.fromFile(fileName, "UTF-8")
  val lines = for(l <- source.getLines.toArray) yield l.replaceAll("\t", "        ")
  source.close
  val out = new java.io.PrintWriter(fileName)
  for(l <- lines) out.println(l)
  out.close
}
