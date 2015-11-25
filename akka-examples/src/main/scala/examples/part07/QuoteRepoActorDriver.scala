package examples.part07


import akka.actor._
import examples.{QuoteRequest, QuoteResponse}
import scala.concurrent.Await
import scala.util.Random
import scala.concurrent.duration._

class QuoteRepositoryActor extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  var repoRequestCount: Int = 1

  def receive = {

    case QuoteRequest => {
      if (repoRequestCount > 3) {
        self ! PoisonPill
      } else {
        //Get a random Quote from the list and construct a response
        val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
        log.info(s"QuoteRequest received in QuoteRepositoryActor. Sending response to Teacher Actor $quoteResponse")
        repoRequestCount = repoRequestCount + 1
        sender ! quoteResponse
      }
    }
  }
}


class TeacherActorWatcher extends Actor with ActorLogging {

  val quoteRepositoryActor = context.actorOf(Props[QuoteRepositoryActor], "quoteRepositoryActor")
  context.watch(quoteRepositoryActor)

  def receive = {
    case QuoteRequest =>
      quoteRepositoryActor ! QuoteRequest
    case Terminated(terminatedActorRef) =>
      log.error(s"Child Actor {$terminatedActorRef} Terminated")
  }
}

object QuoteRepoActorDriver {
  def main(args: Array[String]) {
    val system = ActorSystem("QuoteSystem")
    val watcherRef = system.actorOf(Props[TeacherActorWatcher])
    watcherRef ! QuoteRequest
    watcherRef ! QuoteRequest
    watcherRef ! QuoteRequest
    watcherRef ! QuoteRequest

    Thread.sleep((5 seconds) toMillis)
    Await.ready(system.terminate(), 2 seconds)
  }
}
