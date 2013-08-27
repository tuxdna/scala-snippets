package ch09

/*
 * 4. Write a Scala program that reads a text file containing only
 * floating-point numbers. Print the sum, average, maximum, and minimum
 * of the numbers in the file.
 */

import scala.io.Source

object ex04 extends App {
  var fileName = "numbers.txt"
  if (args.length < 1) {
    println("No input from the command line. Exiting.")
    sys.exit(-1)
  }
  fileName = args(0)
  var source = Source.fromFile(fileName, "UTF-8")
  val numbers = (for(line <- source.getLines; 
		 word <- line.split("\\s+")) yield word.toDouble).toList

  println(numbers.getClass)
  println(numbers.length)
  val sum = numbers.sum
  val avg = sum / numbers.length
  val min = numbers.min
  val max = numbers.max
  println(sum)
  println(avg)
  println(max)
  println(min)
}
