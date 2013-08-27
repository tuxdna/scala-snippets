/*
 * 3. Write a Scala code snippet that reads a file and prints all words
 * with more than 12 characters to the console. Extra credit if you can
 * do this in a single line.
 *
 */

import scala.io.Source

object ex03 extends App {
  var fileName = ""
  if (args.length < 1) {
    println("No input from the command line. Exiting.")
    sys.exit(-1)
  }
  fileName = args(0)
  var source = Source.fromFile(fileName, "UTF-8")
  for(line <- source.getLines; word <- line.split("\\s+") if (word.length > 12)) println(word)
}
