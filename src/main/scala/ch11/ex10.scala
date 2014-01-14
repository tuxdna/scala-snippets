package ch11

import java.io.File

//  https://stackoverflow.com/questions/21105773/in-scala-how-to-use-unapplyseq-with-pattern-matching-like-list-start-pattern-a

object ex10 extends App {

  class RichFile(filePath: String) {
    val file = new File(filePath)
    override def toString = "RichFile(" + filePath + ")"
  }

  object RichFile {
    def apply(filePath: String) = new RichFile(filePath)
    def unapply(rf: RichFile) = {
      if (rf == null || rf.file.getAbsolutePath.isEmpty) None
      else {
        val basename = rf.file.getName
        val dirname = rf.file.getParent
        val (name, ext) = basename.split("\\.", 2) match {
          case Array(name, ext) => (name, ext)
          case Array(name) => (name, "")
        }
        Some((dirname, name, ext))
      }
    }
  }

  object RichFilePath {
    def unapplySeq(rf: RichFile): Option[Seq[String]] = {
      def rec(f: File): List[String] = {
        if (f == null || f.getName() == "") List()
        else rec(f.getParentFile()) ++ List(f.getName)
      }
      val filePath = rf.file.getAbsolutePath()
      if (filePath.isEmpty) None
      else Some(rec(rf.file))
    }
  }

  object --> {
    def unapply(rf: RichFile): Option[(RichFile, String)] = {
      if (rf.file.getAbsolutePath.isEmpty)
        None
      else {
        val f = rf.file.getAbsoluteFile
        if (f.getParent() == null)
          None
        else
          Some((RichFile(f.getParent), f.getName))
      }
    }
  }

  val l = List(
    RichFile("/abc/def/name.txt"),
    RichFile("/home/cay/name.txt"),
    RichFile("/a/b/c/d/e"),
    RichFile("/y/z"),
    RichFile("/y"))

  l.foreach { f =>
    f match {
      case RichFilePath(a, b, c, d, l @ _*) => println("RichFilePath -> " + (a, b, c, d, l))
      case a --> b --> c => println(f" --> $a\t$b\t$c")
      case RichFile(f) => println("RichFile -> " + f)
    }
  }

}
