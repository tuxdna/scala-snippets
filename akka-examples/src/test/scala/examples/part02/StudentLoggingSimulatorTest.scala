package examples.part02

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import examples.QuoteRequest
import examples.part01.StudentSimulator.TeacherActor
import examples.part02.StudentLoggingSimulator.{TeacherLoggingActor}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}


class StudentLoggingSimulatorTest extends TestKit(ActorSystem("MySystem",
  ConfigFactory.parseString(
    """
      |akka{
      | loggers = [
      |     "akka.testkit.TestEventListener",
      |     "akka.event.slf4j.Slf4jLogger"
      |     ]
      | loglevel = "DEBUG"
      | logging-filter = "akka.event.slf4j.Slf4jLoggingFilter"
      |}
      |     """.stripMargin)))
with WordSpecLike with MustMatchers with BeforeAndAfterAll {

  "A teacher" must {
    "print a quote when a QuoteRequest message is sent" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! QuoteRequest
    }
  }

  "A teacher with ActorLogging" must {
    "log a quote when QuoteRequest message is sent" in {
      val teacherRef = TestActorRef[TeacherLoggingActor]
      teacherRef ! QuoteRequest
    }

    "have a qoute list of size 4" in {
      val teacherRef = TestActorRef[TeacherLoggingActor]
      teacherRef.underlyingActor.qouteList must have size (4)

    }

    "be verifiable via EventFilter in response to a QuoteRequest that is sent" in {
      val teacherRef = TestActorRef[TeacherLoggingActor]
      EventFilter.info(pattern = """.*?QuoteResponse.*?""", occurrences = 1) intercept {
        teacherRef ! QuoteRequest
      }
    }

    "has sate same as provided state" in {
      val quotes = List(
        "Moderation is for cowards",
        "Anything worth doing is worth overdoing",
        "The trouble is you think you have time",
        "You never gonna know if you never even try")

      val teacherRef = TestActorRef(new TeacherLoggingActorWithParams(quotes))
      teacherRef.underlyingActor.qouteList must have size (quotes.length)
      EventFilter.info(pattern = """.*?QuoteResponse.*?""", occurrences = 1) intercept {
        teacherRef ! QuoteRequest
      }
    }
  }
}

