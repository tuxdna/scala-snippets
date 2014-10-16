package reactive

import rx.lang.scala.Observable
import scala.language.postfixOps
import scala.concurrent.duration._
import scala.io.Source

object observableWordCount extends App {
  val words = Source.fromFile("/usr/share/dict/words").getLines.toStream
  val w = Observable.from(words)
  val l = w map{ x =>
    println(x, x.length)
    x.length()
  }
  val c = l.foldLeft(0)((a, b) => a + b)
  c.subscribe(x => println(x))
}
