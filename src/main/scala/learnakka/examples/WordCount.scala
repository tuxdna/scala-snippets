package learnakka.examples

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.util.Timeout

object WordCount {

  case class Line(s: String)
  case class WordCount(c: Int)
  class StringCounterActor extends Actor {
    def receive = {
      case Line(s) =>
        val c = s.split("""\s+""").length
        sender ! WordCount(c)
      case _ =>
        println("Error: Message not recognized")
    }
  }

  case class BeginFileWordCount()

  class WordCounterActor(filename: String) extends Actor {
    private var running = false
    private var totalLines = 0
    private var linesProcessed = 0
    private var result = 0
    private var fileSenderOpt: Option[ActorRef] = None

    def receive = {
      case BeginFileWordCount() =>
        if (running) {
          println("Warning: duplicate start message recieved")

        } else {
          running = true
          fileSenderOpt = Some(sender)
          import scala.io.Source._
          fromFile(filename).getLines.foreach { line =>
            context.actorOf(Props[StringCounterActor]) ! Line(line)
            totalLines += 1
          }

        }
      case WordCount(wc) => {
        result += wc
        linesProcessed += 1
        if (linesProcessed == totalLines) {
          for (fileSender <- fileSenderOpt) fileSender ! result
        }
      }
      case _ => println("Error: Message not recognized")

    }
  }

  import scala.concurrent.duration._
  import akka.pattern.ask
  import akka.dispatch.ExecutionContexts._
  implicit val ec = global

  def main(args: Array[String]) {
    val system = ActorSystem("system")
    val filename = "/home/tuxdna/work/learn/mine/scala-snippets/src/main/scala/learnakka/examples/WordCount.scala" //args(0)
    val actor = system.actorOf(Props(new WordCounterActor(filename)))
    implicit val timeout = Timeout(25 seconds)
    val future = actor ? BeginFileWordCount()
    future.map { result =>
      println("Total number of words " + result)
      system.shutdown
    }

  }
}