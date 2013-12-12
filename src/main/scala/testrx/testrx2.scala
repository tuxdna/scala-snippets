//package testrx
//import akka.actor._
//
//sealed trait SubUnsub extends Message
//
//case class Subscribe(onNext: Message => Unit) extends SubUnsub
//case object Unsubscribe extends SubUnsub
//
//class ObservableActor2 extends Actor with ActorLogging {
//  def receive = {
//    case Subscribe(onNext) =>
//      log debug "subscribe"
//      context become subscribed(onNext)
//  }
//
//  def subscribed(onNext: Message => Unit): Actor.Receive = {
//    case Unsubscribe =>
//      log debug "unsubscribe"
//      context become receive
//
//    case message: Message =>
//      log debug s"incoming: $message"
//      onNext(message)
//  }
//}
//
//import rx.lang.scala._ // the additional import for RxJava
//
//object Main extends App {
//  def observableFromActor(actor: ActorRef): Observable[Message] =
//    Observable { observer =>
//      actor ! Subscribe(observer onNext)
//      new Subscription {
//        override def unsubscribe: Unit = actor ! Unsubscribe
//      }
//    }
//}