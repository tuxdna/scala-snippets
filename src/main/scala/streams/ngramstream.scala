package streams

object ngramstream extends App {

  def process(st: Stream[Array[String]])(f: Array[String] => Unit): Stream[Array[String]] = st match {
    case x #:: xs => {
      f(x)
      process(xs)(f)
    }
    case _ => Stream[Array[String]]()
  }

  def ngrams(n: Int, words: Array[String]) = {
    // exclude 1-grams
    (2 to n).map { i => words.sliding(i).toStream }
      .foldLeft(Stream[Array[String]]()) {
        (a, b) => a #::: b
      }
  }

  val words = "the bee is the bee of the bees"
  val n = 4
  val ngrams2 = ngrams(n, words.split(" "))

  process(ngrams2) { x =>
    println(x.toList)
  }

}