package reactive

import scala.language.postfixOps
import rx.lang.scala.Observable
import scala.concurrent._
import duration._

/* This worksheet demonstrates some (perhaps) unexpected
* behavior from Observables generated by the methods
* onErrorReturn and onErrorResumeNext:
* Observables generated in this manner are not traversable
* indpendently of the original Observable or other Observables
* derived in this manner.
*/

object Observer05 extends App {
  println("Welcome to the Scala worksheet") 

  type P = (String, String)
  type T = (Long, String, String)
  type S = Seq[T]

  val circles: Array[P] = Array(
    ("Red", "Circle"),
    ("Yellow", "Circle"),
    ("Green", "Circle"),
    ("Aqua", "Circle"),
    ("Blue", "Circle"),
    ("Violet", "Circle")) 
  //| reen,Circle), (Aqua,Circle), (Blue,Circle), (Violet,Circle))

  // This formatted printout is not as pretty as marble diagrams,
  // but attempts to capture the same content given what we have availabe
  // in the worksheet mode.
  // We use variable indentation to indicate the output of different observables.
  // There is a slight offset before each printing starts, to ensure things
  // come out in the right order.
  // This is pretty kludgy - if you don't like it, write a better one and
  // submit a pull request!
  def printOutMarbles(i: Int)(obs: Observable[T])(num: Int)(indent: Int): Unit = {
    blocking { Thread.sleep(20) }
    val is: String = i.toString.padTo(indent, ' ')

    val obsP =
      if (num > 0) obs.take(num)
      else obs
    obsP.subscribe(
      it => {
        val color = it._2
        val shape = it._3
        println(f"$is $color $shape")
      },
      error => println(f"$is Ooops"),
      () => println(f"$is Completed"))
  } 
  //| ng)])(num: Int)(indent: Int)Unit

  def block(i: Int)(num: Int) = {
    println("Observable: " + i.toString)

    val ticks: Observable[Long] = Observable.interval(1 second)
    val marbles: Observable[T] = ticks.take(6).map(i => (i, circles(i.toInt)._1, circles(i.toInt)._2))
    val squareMarbles: Observable[T] = marbles.map(s => (s._1, s._2, "Square"))
    val fails: Observable[T] = marbles.take(3) ++ Observable(new Exception("My Bad")) ++ squareMarbles
    val eReturn: Observable[T] = fails.onErrorReturn(e => (-99, "Black", "Diamond"))
    val eResume: Observable[T] = fails.onErrorResumeNext(squareMarbles)
    // Unlike the iterable case, we are able to "traverse" the observable
    // multiple "times" through multiple subscriptions.
    if ((i == 0) || (i == 3) || (i == 4)) printOutMarbles(i)(fails)(num)(1)
    if ((i == 1) || (i == 3) || (i == 5) || (i == 6)) printOutMarbles(i)(eReturn)(num)(11)
    if ((i == 2) || (i == 4) || (i == 5)) printOutMarbles(i)(eResume)(num)(21)
    if ((i == 6)) printOutMarbles(i)(fails)(num)(31)

  } 

  // Subscribing only to failing Observable
  val gap = 5000 
  block(0)(-1) 
  blocking { Thread.sleep(gap) }

  // Subscribing only to Observable modified by onErrorReturn
  block(1)(-1) 
  blocking { Thread.sleep(gap) }
  
  // Subscribing only to Observable modified by onErrorResumeNext
  block(2)(-1) 
  blocking { Thread.sleep(gap) }

  // Subscribing first to failing Observable, then to Observable modified by onErrorReturn
  block(3)(-1) 
  //| 2                     Yellow Square
  blocking { Thread.sleep(gap) }

  // Subscribing first to failing Observable, then to Observable modified by onErrorResumeNext
  block(4)(-1) 
  blocking { Thread.sleep(gap) } 
  
  // Subscribing first to Observable modified by onErrorReturn, then to Observable modified by onErrorResumeNext

  block(5)(-1) 
  blocking { Thread.sleep(gap) }
  
  // Subscribing first to Observable modified by onErrorReturn, then to the original failing Observable
  block(6)(-1) 
  blocking { Thread.sleep(gap) } 
  
  println("Done") 

}