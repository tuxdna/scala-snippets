package ch10

import java.io.FileInputStream
import java.io.File
import java.io.InputStream
import java.io.BufferedInputStream

object ex08 extends App {
  trait Buffered extends InputStream {
    val bis = new BufferedInputStream(this)
    override def read = {
      bis.read()
    }
  }

  val is = new FileInputStream(new File("/etc/fstab")) with Buffered
  var c = is.read
  while (c != -1) { print(c.toChar); c = is.read }
}
