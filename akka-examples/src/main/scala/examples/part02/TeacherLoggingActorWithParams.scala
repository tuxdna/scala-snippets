package examples.part02

import akka.actor.{Actor, ActorLogging}
import examples.{QuoteRequest, QuoteResponse}

class TeacherLoggingActorWithParams(quotes: List[String] = List("No quote is still aa qoute."))
  extends Actor with ActorLogging {
  lazy val _quotes = quotes

  def qouteList() = _quotes

  def receive = {
    case QuoteRequest =>
      import util.Random
      val response = QuoteResponse(quotes(Random.nextInt(quotes.length)))
      log.info(response.toString)
  }
}
