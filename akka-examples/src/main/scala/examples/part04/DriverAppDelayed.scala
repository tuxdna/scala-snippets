package examples.part04

import akka.actor.{Actor, ActorLogging, ActorRef, _}
import examples.{InitSignal, QuoteRequest, QuoteResponse}

import scala.concurrent.Await
import scala.concurrent.duration._

class StudentDelayedActor(teacherRef: ActorRef) extends Actor with ActorLogging {
  def receive = {
    case InitSignal =>
      // teacherRef ! QuoteRequest
      import context.dispatcher
      context.system.scheduler.scheduleOnce(5 seconds, teacherRef, QuoteRequest)
    /*
     We can also schedule Periodically every 5 seconds, starting from right now
     context.system.scheduler.schedule(0 seconds, 5 seconds, teacherActorRef, QuoteRequest)
     */

    case QuoteResponse(q) =>
      log.info("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $q")
  }
}

class TeacherActor extends Actor with ActorLogging {
  def receive = {
    case QuoteRequest =>
      import util.Random
      val response = QuoteResponse(quotes(Random.nextInt(quotes.length)))
      sender() ! response
  }

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  def qouteList() = quotes
}

object DriverAppDelayed {
  def main(args: Array[String]) {
    val actorSystem = ActorSystem("MySystem")
    val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])
    val studentActorRef = actorSystem.actorOf(Props(new StudentDelayedActor(teacherActorRef)))
    studentActorRef ! InitSignal
    Thread.sleep(2000)
    val terminateFuture = actorSystem.terminate()
    Await.result(terminateFuture, 10 seconds)
  }
}

