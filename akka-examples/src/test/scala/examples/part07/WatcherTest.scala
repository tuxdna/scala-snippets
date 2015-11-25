package examples.part07

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestActorRef, TestKit, TestProbe}
import com.typesafe.config.ConfigFactory
import examples.{QuoteRequest, QuoteResponse}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

import scala.concurrent.duration._


class WatcherTest extends TestKit(ActorSystem("MySystem",
  ConfigFactory.parseString(
    """
      |akka{
      | test {
      |  filter-leeway = 7s
      | }
      | loggers = [
      |     "akka.testkit.TestEventListener",
      |     "akka.event.slf4j.Slf4jLogger"
      |     ]
      | loglevel = "INFO"
      | logging-filter = "akka.event.slf4j.Slf4jLoggingFilter"
      |}
      |     """.stripMargin)))
with WordSpecLike with MustMatchers with BeforeAndAfterAll {

  "A QuoteRepositoryActor" must {
    "send back a termination message to the watcher on 4th message" in {
      val quoteRepository = TestActorRef[QuoteRepositoryActor]

      val testProbe = TestProbe()
      testProbe.watch(quoteRepository) //Let's watch the Actor

      within(1000 millis) {
        var receivedQuotes = List[String]()
        (1 to 3).foreach(_ => quoteRepository ! QuoteRequest)
        receiveWhile() {
          case QuoteResponse(quoteString) => {
            receivedQuotes = receivedQuotes :+ quoteString
          }
        }

        receivedQuotes.size must be(3)
        println(s"receiveCount ${receivedQuotes.size}")

        //4th message
        quoteRepository ! QuoteRequest
        testProbe.expectTerminated(quoteRepository) //Expect a Terminated Message
      }
    }

    "not send back a termination message on 4th message if not watched" in {
      val quoteRepository = TestActorRef[QuoteRepositoryActor]

      val testProbe = TestProbe()
      testProbe.watch(quoteRepository) //watching

      within(1000 millis) {
        var receivedQuotes = List[String]()
        (1 to 3).foreach(_ => quoteRepository ! QuoteRequest)
        receiveWhile() {
          case QuoteResponse(quoteString) => {
            receivedQuotes = receivedQuotes :+ quoteString
          }
        }

        testProbe.unwatch(quoteRepository) //not watching anymore
        receivedQuotes.size must be(3)
        println(s"receiveCount ${receivedQuotes.size}")

        //4th message
        quoteRepository ! QuoteRequest
        testProbe.expectNoMsg() //Not Watching. No Terminated Message
      }
    }


    "end back a termination message to the watcher on 4th message to the TeacherActor" in {

      //This just subscribes to the EventFilter for messages. We have asserted all that we need against the QuoteRepositoryActor in the previous testcase
      val teacherActor = TestActorRef[TeacherActorWatcher]

      within(1000 millis) {
        (1 to 3).foreach(_ => teacherActor ! QuoteRequest) //this sends a message to the QuoteRepositoryActor

        EventFilter.error(pattern = """Child Actor .* Terminated""", occurrences = 1).intercept {
          teacherActor ! QuoteRequest //Send the dangerous 4th message
        }
      }
    }
  }


}
