package learnakka.examples

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._
import scala.io.Source

object WordCountNoState {

  class LineWordsCounter extends Actor {
    def receive = {
      case (lineNumber: Int, s: String) =>
        val wc = s.split("""\s+""").length
        val msg = (lineNumber, wc)
        println(s"msg: ${msg}")
        sender ! (lineNumber, wc)
      case _ =>
        println("Error: Message not recognized")
    }
  }

  case class WordCount(c: Int)

  class WordCounterActor extends Actor {

    def waitForLines(total: Int, linesRemaining: Int, master: ActorRef): Receive = {
      case (lineNumber: Int, wc: Int) => {
        val newTotal = total + wc
        val newLinesRemaining = linesRemaining - 1
        if (newLinesRemaining > 0) {
          println(s"total: ${newTotal}")
          context.become(waitForLines(newTotal, newLinesRemaining, master))
        } else {
          master ! WordCount(newTotal)
        }
      }
      case _ => println("Error: Message not recognized")
    }

    def fileSplit: Receive = {
      case filename: String =>

        var lineCount = 0
        Source.fromFile(filename)
          .getLines
          .zipWithIndex
          .foreach { x =>
            val (line, ln) = x
            context.actorOf(Props[LineWordsCounter]) ! (ln, line)
            lineCount += 1
          }
        context.become(waitForLines(0, lineCount, sender))

      case _ => println("Error: Message not recognized")
    }

    def receive = fileSplit
  }

  class FileWordCounter(filename: String) extends Actor {
    val counter = context.actorOf(Props[WordCounterActor], "counter")
    counter ! filename

    def receive = {
      case WordCount(count) =>
        println(s"count was $count")
        context.system.shutdown
    }
  }

  def main(args: Array[String]) {
    val system = ActorSystem("system")
    val defaultFilename = "/home/tuxdna/work/learn/external/ml-data/synthetic_control.data" // "/proc/cpuinfo"
    val filename = if (args.length > 0) args(0) else defaultFilename
    val actor = system.actorOf(Props(new FileWordCounter(filename)))
  }

}