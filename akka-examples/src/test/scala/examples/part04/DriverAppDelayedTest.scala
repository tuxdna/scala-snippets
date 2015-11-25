package examples.part04

import akka.actor.{ActorSystem, Props}
import akka.testkit.{EventFilter, TestKit}
import com.typesafe.config.ConfigFactory
import examples.InitSignal
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}


class DriverAppDelayedTest extends TestKit(ActorSystem("MySystem",
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

  "A student" must {
    " log a QuoteRespose after it receives an InitSignal message" in {
      val teacherRef = system.actorOf(Props[TeacherActor])
      val studentRef = system.actorOf(Props(new StudentDelayedActor(teacherRef)))
      EventFilter.info(pattern = """Printing from Student Actor.*""", occurrences = 1).intercept {
        studentRef ! InitSignal
      }
    }
  }

}
