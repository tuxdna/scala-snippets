import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object myactors extends App {
  class HiActor extends Actor {
    def receive = {
      case "Hi" => println("Hello")
      case _ => println("Unknown message")
    }
  }

  val system = ActorSystem("myActorSystem")

  val actor1 = system.actorOf(Props[HiActor], name = "hiActor")

  println("Started the actor actor1")

  actor1 ! "Hi"
  actor1 ! "Bye"

  case class Deposit(val amount: Double)
  case class Withdraw(val amount: Double)
  class AccountActor extends Actor {
    private var balance = 0.0
    def receive = {
      case Deposit(amount) => balance += amount
      case Withdraw(amount) => balance -= amount
      case "Balance" => println("balance = " + balance)
      case _ => println("Unknown message")
    }
  }

  val actor2 = system.actorOf(Props[AccountActor], name = "hiActor")

  println("Started the actor actor2")
  actor2 ! Deposit(100)
  for (i <- (1 to 10)) {
    actor2 ! Withdraw(i)
  }
  actor2 ! Deposit(100)
  actor2 ! "Hi"
  actor2 ! "Balance"

}
