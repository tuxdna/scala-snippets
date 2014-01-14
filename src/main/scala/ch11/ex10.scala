package ch11

import java.io.File

// TODO: In progress
object ex10 extends App {
  class RichFile(filePath: String) {
    val f = new File(filePath)
    def unapply() = {
      val basename = f.getName()
      val dirname = f.getParent()
      val ei = basename.indexOf(".")
      if (ei >= 0) {
        Some((dirname, basename.substring(0, ei), basename.substring(ei + 1)))
      } else {
        Some((dirname, basename, ""))
      }
    }
    def unapplySeq(): Option[Seq[String]] = {
      val filePath = f.getAbsolutePath()
      if (filePath.length() == 0)
        None
      else
        Some(filePath.split("/"))
    }
  }

  object RichFile {
    def apply(filePath: String) = new RichFile(filePath)
    def unapply(f: RichFile) = {
      f.unapply
    }
    def unapplySeq(f: RichFile) = {
      f.unapplySeq()
    }
  }

  val l = List(
    RichFile("/abc/def/name.txt"),
    RichFile("/home/cay/name.txt"),
    RichFile("/a/b/c/d/e"))

  l.foreach { f =>
    f match {
      // case x :: y => println((x,y))
      case RichFile(a, b, c) => println((a, b, c))
    }
  }

}
