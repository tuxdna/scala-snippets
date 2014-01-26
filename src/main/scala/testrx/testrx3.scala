package testrx

import akka.actor._
import rx.lang.scala._

object testrx3 extends App {

  sealed trait Message
  case class Hello(text: String) extends Message

  sealed trait SubUnsub extends Message
  case class Subscribe(onNext: Message => Unit) extends SubUnsub
  case object Unsubscribe extends SubUnsub

  class ObservableActor3 extends Actor with ActorLogging {
    def receive = {
      case Subscribe(onNext) =>
        log debug "subscribe"
        context become subscribed(onNext)
    }

    def subscribed(onNext: Message => Unit): Actor.Receive = {
      case Unsubscribe =>
        log debug "unsubscribe"
        context become receive

      case message: Message =>
        log debug s"incoming: $message"
        onNext(message)
    }
  }

  val system = ActorSystem("client")
  val receiver = system actorOf (Props[ObservableActor], "rcv")

  val subscription =
    observableFromActor(receiver)
      .take(3)
      .subscribe(msg => println(s"received: $msg"))

  Seq("hello", "world", "again", "not anymore") foreach { msg =>
    receiver ! Hello(msg)
  }
  subscription.unsubscribe
  system.shutdown

  def observableFromActor(actor: ActorRef): Observable[Message] =
    Observable { observer =>
      actor ! Subscribe(observer onNext)
      new Subscription {
        override def unsubscribe: Unit = actor ! Unsubscribe
        val asJavaSubscription: rx.Subscription = ???
        def isUnsubscribed: Boolean = ???

      }
    }
}

