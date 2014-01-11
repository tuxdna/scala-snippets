package myactors

import akka.actor.Actor
import akka.actor.Props

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

object become extends App {
  akka.Main.main(Array[String]("myactors.Main"))
}
