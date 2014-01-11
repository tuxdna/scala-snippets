package reactive

import scala.language.postfixOps
import scala.io.Source
import java.net.URL

object Observer01 extends App {
  def printOut[T](i: Int)(iter: Iterator[T])(num: Int): Unit = {
    val iterP = if (num > 0) iter.take(num) else iter
    while (iterP.hasNext) {
      println("i = " + i.toString + ", next = " + iterP.next().toString)
    }
  }

  def ReadLinesFromDisk(path: String): Iterator[String] = {
    Source.fromFile(path).getLines()
  }

  val url = new URL("file:///etc/fstab")
  val path = url.getPath()

  def block(i: Int)(num: Int) = {
    println("Iterable: " + i.toString)

    val iter0 = (0 to (i + 2) by 2).iterator
    val iter1 = (1 to (i + 3) by 2).iterator
    val iterInf = Iterator.continually { Thread.sleep(100); 1 }

    val iter = i match {
      case 0 => iter0.flatMap(n => (n * 10 to (n * 10 + 5) by 1).iterator)
      case 1 => iter0.map(n => n * n)
      case 2 => iter0 ++ iter0 //this just returns iter0 - why is that?
      case 3 => iter0 ++ iter1
      case 4 => iter0.filter(n => n % 4 == 0)
      case 5 => iter0.take(3)
      case 6 => iter0.takeWhile(n => (n < 5))
      case 7 => iter0.zip(iter1)
      case 8 => ReadLinesFromDisk(path)
      case 9 => iterInf.filter(n => (n < 0))
    }
    printOut(i)(iter)(num)
  }

  (0 to 3 toList).foreach(i => block(i)(-1))

  (4 to 8 toList).foreach(i => block(i)(5))
  println("Done")

}