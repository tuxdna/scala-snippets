package examples.part01

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import examples.QuoteRequest

import scala.concurrent.Await
import scala.concurrent.duration._


object StudentSimulator {

  class TeacherActor extends Actor {
    val quotes = List(
      "Moderation is for cowards",
      "Anything worth doing is worth overdoing",
      "The trouble is you think you have time",
      "You never gonna know if you never even try")

    def receive = {
      case QuoteRequest =>
        import util.Random
        val response = quotes(Random.nextInt(quotes.length))
        println(response)
    }
  }


  def main(args: Array[String]) {
    val actorSystem = ActorSystem("MySystem")
    val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])
    teacherActorRef ! QuoteRequest
    Thread.sleep(2000)
    val terminateFuture = actorSystem.terminate()
    Await.result(terminateFuture, 10 seconds)
  }
}
