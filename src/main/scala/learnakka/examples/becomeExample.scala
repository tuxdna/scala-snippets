package learnakka.examples

import akka.actor.Actor
import akka.actor.Props
import akka.actor.actorRef2Scala

object becomeExample {

  class Counter extends Actor {
    def counter(n: Int): Receive = {
      case "incr" => context.become(counter(n + 1))
      case "get" => sender ! n
    }
    def receive = counter(0)
  }

  class Main extends Actor {
    val counter = context.actorOf(Props[Counter], "counter")
    counter ! "incr"
    counter ! "incr"
    counter ! "incr"
    counter ! "get"
    def receive = {
      case count: Int =>
        println(s"count was $count")
        context.stop(self)
    }
  }

  def main(args: Array[String]) {
    akka.Main.main(Array[String](classOf[Main].getName()))
  }

}
