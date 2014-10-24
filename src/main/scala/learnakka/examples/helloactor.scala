package learnakka.examples

import akka.actor.Actor
import akka.actor.Props
import akka.actor.actorRef2Scala

class HelloWorld extends Actor {
  override def preStart(): Unit = {
    // create the greeter actor
    val greeter = context.actorOf(Props[Greeter], "greeter")
    // tell it to perform the greeting
    greeter ! Greeter.Greet
  }
  def receive = {
    // when the greeter is done, stop this actor and with it the application
    case Greeter.Done ⇒ context.stop(self)
  }
}

object Greeter {
  case object Greet
  case object Done
}

class Greeter extends Actor {
  def receive = {
    case Greeter.Greet ⇒
      println("Hello World!")
      sender ! Greeter.Done
  }
}

object runner extends App {
  akka.Main.main(Array[String](classOf[HelloWorld].getName()))
}