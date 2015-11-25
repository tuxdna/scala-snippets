package examples.part02

import akka.actor.{ActorLogging, ActorSystem, Actor, Props}
import examples.{QuoteResponse, QuoteRequest}

import scala.concurrent.Await
import scala.concurrent.duration._


object StudentLoggingSimulator {


  class TeacherLoggingActor extends Actor with ActorLogging {
    val quotes = List(
      "Moderation is for cowards",
      "Anything worth doing is worth overdoing",
      "The trouble is you think you have time",
      "You never gonna know if you never even try")

    def qouteList = quotes

    def receive = {
      case QuoteRequest =>
        import util.Random
        val response = QuoteResponse(quotes(Random.nextInt(quotes.length)))
        log.info(response.toString)
    }
  }


  def main(args: Array[String]) {

    val actorSystem = ActorSystem("MySystem")
    val teacherActorRef = actorSystem.actorOf(Props[TeacherLoggingActor])
    teacherActorRef ! QuoteRequest
    Thread.sleep(2000)
    val terminateFuture = actorSystem.terminate()
    Await.result(terminateFuture, 10 seconds)

  }
}
