package testrx

import akka.actor.Actor
import akka.actor.ActorLogging

import akka.actor._

class ObservableActor extends Actor with ActorLogging {
  trait Message

  case class Hello(text: String) extends Message
  def receive = {
    case message: Message =>
      log debug s"incoming: $message"
  }
}


