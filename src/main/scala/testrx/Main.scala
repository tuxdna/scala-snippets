//package testrx
//import akka.actor._
//object Main extends App {
//  val system = ActorSystem("client")
//
//  val receiver = system actorOf (Props[ObservableActor], "rcv")
//  Seq("hello", "world", "again", "not anymore") foreach { msg =>
//    receiver ! Hello(msg)
//  }
//
//  system.shutdown
//}