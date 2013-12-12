//package testrx
//
//import akka.actor.Actor
//import akka.actor.ActorLogging
//import akka.actor.ActorSystem
//import akka.actor.Props
//import akka.actor.actorRef2Scala
//
//trait Message
//
//case class Hello(text: String) extends Message
//
//import akka.actor._
//
//class ObservableActor extends Actor with ActorLogging {
//  def receive = {
//    case message: Message =>
//      log debug s"incoming: $message"
//  }
//}
//
//
