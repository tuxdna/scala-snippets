package ch09

/*
 * 8. Write a Scala program that prints the src attributes of all img
 * tags of a web page. Use regular expressions and groups.
 */

import scala.io.Source

object ex08 extends App {
  var url = ""
  if (args.length < 1) {
    println("No input from the command line. Exiting.")
    sys.exit(-1)
  }
  url = args(0)
  var source = Source.fromURL(url, "UTF-8")
  val imgRegex = """<img src=\"(.*?)\".*/>""".r
  for( m <- imgRegex.findAllIn(source.mkString).matchData) {
    println("Image URL: %s".format(m.group(1)))
  }
}
