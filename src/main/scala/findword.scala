import scala.io.Source


object findword extends App {
  val lines = Source.fromFile("/usr/share/dict/words").getLines

  // create a set of all the characters
  // val t = "zxcvbnm".toSet
  val t = "qwerty".toSet

  // now check if any word has characters which are subset of target set above
  // and select those words
  val targetWords = lines
    .filter(_.length > 1) // not interested in single characters
    .filter(_.toLowerCase.toSet.subsetOf(t))
  targetWords foreach println

}
