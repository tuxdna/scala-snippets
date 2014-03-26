package ch13
import scala.collection.mutable
import scala.io.Source
import scala.collection.JavaConversions._

object ex10 {

  def main(args: Array[String]): Unit = {
    val frequencies = mutable.HashMap[Char, Int]()
    val s = "Why is this a terrible idea?"

    for (c <- s.par) {
      frequencies(c) = frequencies.getOrElse(c, 0) + 1
    }

    println(frequencies)

    val r = s.par.map((_, 1))
      .groupBy(_._1)
      .map(x => x._1 -> x._2.foldLeft(0)((x, y) => x + y._2))
      
    println(r)
  }

}