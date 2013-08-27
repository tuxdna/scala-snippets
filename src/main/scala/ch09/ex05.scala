package ch09

/*
 * 5. Write a Scala program that writes the powers of 2 and their
 * reciprocals to a file, with the exponent ranging from 0 to 20. Line
 * up the columns:
1
2
4
...
1
0.5
0.25
...

*/

import scala.io.Source
import java.io.PrintWriter

object ex05 extends App {
  val out = new PrintWriter("numbers.txt")
  for(i <- 0 to 20) {
    val p = Math.pow(2,i)
    val r = 1.0 / p
    out.println("%10s %20s".format(p.toInt,r))
  }
  out.close
}
