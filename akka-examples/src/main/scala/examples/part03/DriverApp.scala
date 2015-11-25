package examples.part03

import akka.actor._
import examples.{InitSignal, QuoteResponse, QuoteRequest}

import scala.concurrent.Await
import scala.concurrent.duration._

class StudentActor(teacherRef: ActorRef) extends Actor with ActorLogging {
  def receive = {
    case InitSignal =>
      teacherRef ! QuoteRequest
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

object DriverApp {
  def main(args: Array[String]) {
    val actorSystem = ActorSystem("MySystem")
    val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])
    val studentActorRef = actorSystem.actorOf(Props(new StudentActor(teacherActorRef)))
    studentActorRef ! InitSignal
    Thread.sleep(2000)
    val terminateFuture = actorSystem.terminate()
    Await.result(terminateFuture, 10 seconds)
  }
}
