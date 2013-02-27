//comment
object Upper {
  def main(args: Array[String]) {
    val uplist = args.map(_.toUpperCase()).foreach(printf("%s ",_))
    println("")
  }
}

