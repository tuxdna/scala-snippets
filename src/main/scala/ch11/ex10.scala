package ch11


// TODO: In progress
object ex10 extends App {
  class RichFile(val f: String) {
    def unapply() = {
      val c = f.split("/")
      val x = c.last.split(".")

    }
    def unapplySeq(): Seq[String] = {
      f.split("/")
    }
  }

  object RichFile {
    def unapply(f: RichFile) = {
      f.unapply
    }
    def unapplySeq(f: RichFile): Seq[String] = {
      f.unapplySeq()
    }
  }

  val f1 = new RichFile("/abc/def/name.txt")
  //  f1 match {
  //    case RichFile(a, b, c) =>
  //  }

}