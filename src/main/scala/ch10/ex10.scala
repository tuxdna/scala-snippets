package ch10

import java.io.InputStream
import java.io.FileInputStream
import java.io.File

class IterableInputStream(is: FileInputStream) extends InputStream with Iterable[Byte] {

  def read: Int = {
    is.read
  }

  def iterator: Iterator[Byte] = {
    val i: Iterator[Byte] = new Iterator[Byte] {
      var nextFlag = true
      def hasNext: Boolean = nextFlag
      def next: Byte = {
        val c = is.read
        nextFlag = (c != -1)
        c.toByte
      }
    }
    i
  }
}

object ex10 extends App {
  val is = new FileInputStream(new File("/etc/fstab"))
  val iis = new IterableInputStream(is)
  val x = iis.map(_.toChar).toList
  println(x)
}