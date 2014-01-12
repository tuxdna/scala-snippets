package ch10

import java.io.FileInputStream
import java.io.File
import java.io.InputStream
import java.io.BufferedInputStream
import ch10._

object ex09 extends App {
  trait Buffered extends InputStream with Logged {
    val bis = new BufferedInputStream(this)
    override def read = {
      bis.read()
    }
  }

  val is = new FileInputStream(new File("/etc/fstab")) with Buffered with ConsoleLogger with TimestampLogger
  var c = is.read
  while (c != -1) {
    print(c.toChar)
    c = is.read
    c match {
      case '\n' => is.log("next line...")
      case -1 => is.log("End of file...")
      case _ => // ignore
    }
  }
}
