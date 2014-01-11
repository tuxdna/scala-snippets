package reactive

import scala.language.postfixOps
import rx.lang.scala.{Observable, Subscription}
import scala.concurrent._
import duration._
import java.util.Calendar

object Observer06 extends App {

  def printOut[T](i:Int)(obs:Observable[T])(num:Int)(indent:Int)(etime: () => Double): Unit = {
    blocking{Thread.sleep(20)}
    val is:String = i.toString.padTo(indent, ' ' )
    val obsP =
      if (num > 0 ) obs.take(num)
      else obs
    obsP.subscribe(
      it => {
        val now = etime()
        val itOut = it.toString
                 println(f"$is ( $now%5.2f ) $itOut")
      },
        error => {
        val now = etime()
                 println(f"$is ( $now%5.2f ) Ooops")
        },
        () =>    {
        val now = etime()
                 println(f"$is ( $now%5.2f ) Completed")
        }
   )
  }

  def block(i: Int)(num: Int) = {
    val t0 = System.nanoTime()
    def etime() = ((System.nanoTime() - t0).toDouble / 1e+9)
    println("Observable: " + i.toString)
    val xs: Observable[Int] = Observable(3,2,1)
    val yss: Observable[Observable[Int]] =
      xs.map(x => Observable.interval(x seconds).map(_=>x).take(2))
    val zs: Observable[Int] =
      if (i == 0) yss.concat
      else yss.flatten

    printOut(i)(xs)(num)(1)(etime)
    printOut(i)(yss)(num)(11)(etime)
    printOut(i)(zs)(num)(21)(etime)
       
	}
  val gap = 15000
	block(0)(-1)
  blocking{Thread.sleep(gap)} 
	block(1)(-1) 
  blocking{Thread.sleep(gap)}
  println("Done")
   
}
