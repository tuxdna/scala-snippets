package ch13
import scala.collection.mutable
import scala.io.Source
import scala.collection.JavaConversions._

object ex09 {
  def main(args: Array[String]) {

    val frequencies1 = new mutable.HashMap[Char, Int] with mutable.SynchronizedMap[Char, Int]


    args.par.foreach { a =>
      val src = Source.fromFile(a)
      src.getLines.foreach { l =>
        l.foreach { c =>
          frequencies1(c) = frequencies1.getOrElse(c, 0) + 1
        }
      }
    }

    println(frequencies1)

    val frequencies2: mutable.ConcurrentMap[Char, Int] = new java.util.concurrent.ConcurrentHashMap[Char, Int]
    args.par.foreach { a =>
      val src = Source.fromFile(a)
      src.getLines.foreach { l =>
        l.foreach { c =>
          frequencies2(c) = frequencies2.getOrElse(c, 0) + 1
        }
      }
    }

    println(frequencies2)

  }
}
