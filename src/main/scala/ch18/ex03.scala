package ch18

object ex03 {
  object Title
  object Author
  class Document {
    private var useNextArgsAs: Any = null
    var title = ""
    var author = ""
    def set(obj: Title.type): this.type = { useNextArgsAs = obj; this }
    def set(obj: Author.type): this.type = { useNextArgsAs = obj; this }
    def to(arg: String): this.type = {
      useNextArgsAs match {
        case Title => title = arg
        case Author => author = arg
        case _ =>
      }
      this
    }

    override def toString() = "Document(author=%s, title=%s)".format(author, title)
  }

  def main(args: Array[String]) {
    val book1 = new Document
    book1 set Title to "BookTitle"
    println(book1)
    book1 set Author to "BookAuthor"
    println(book1)

    val book = new Document
    book set Title to "Scala for the Impatient" set Author to "Cay Horstmann"
    println(book)
  }
}