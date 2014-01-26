import rx.lang.scala.Observable
import scala.language.postfixOps
import scala.concurrent.duration._
import scala.io.Source

object playground {
  {
    val i = 1
    val ticks: Observable[Long] = Observable.interval(10000 millis)

    ticks.subscribe(x => println(x))

    val ints = Observable.from(List(1, 2, 3))
    ints.subscribe(x => println(x))
    // readLine()
  }                                               //> 1
                                                  //| 2
                                                  //| 3
                                                  //| res0: rx.lang.scala.Subscription = rx.lang.scala.Subscription$$anon$2@5ff06d
                                                  //| c3
  val words = Source.fromFile("/usr/share/dict/words").getLines.toStream
                                                  //> words  : scala.collection.immutable.Stream[String] = Stream(1080, ?)

  val w = Observable.from(words)                  //> w  : rx.lang.scala.Observable[String] = rx.lang.scala.JavaConversions$$anon$
                                                  //| 1@c9be79a
 
  val l = w map (x => x.length())                 //> l  : rx.lang.scala.Observable[Int] = rx.lang.scala.JavaConversions$$anon$1@6
                                                  //| da28362
  val c = l.foldLeft(0)((a,b) => a + b)           //> c  : rx.lang.scala.Observable[Int] = rx.lang.scala.JavaConversions$$anon$1@8
                                                  //| 11978b
  c.subscribe(x => println(x))                    //> 4473852
                                                  //| res1: rx.lang.scala.Subscription = rx.lang.scala.Subscription$$anon$2@97494c
                                                  //| 8
}