package testrx
import akka.actor._

object testrx extends App {
  trait Message

  case class Hello(text: String) extends Message
  
  val system = ActorSystem("client")

  val receiver = system actorOf (Props[ObservableActor], "rcv")
  Seq("hello", "world", "again", "not anymore") foreach { msg =>
    receiver ! Hello(msg)
  }

  system.shutdown
}