package messaging.activemq

import javax.jms._
import org.apache.activemq.ActiveMQConnectionFactory
import scala.util.Random
import scala.util.Try

object MessageProducerMain {
  
  val BROKER_URL = "tcp://localhost:61616"
  val NON_TRANSACTED = false
  val NUM_MESSAGES_TO_SEND = 10
  val DELAY = 50
  def username = "admin"
  def password = "password"

  def main(args: Array[String]): Unit = {
    val url = "tcp://localhost:61616" // BROKER_URL
    println("Active Thread Count: " + Thread.activeCount())
    val connectionFactory = new ActiveMQConnectionFactory(username, password, url)
    println("Active Thread Count: " + Thread.activeCount())

    try {
      val connection = connectionFactory.createConnection()
      connection.start()
      println("Active Thread Count: " + Thread.activeCount())
      val session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE)
      val destination = session.createTopic("test-topic")
      val producer = session.createProducer(destination)
      println("Active Thread Count: " + Thread.activeCount())

      println('A)
      for (i <- 0 to NUM_MESSAGES_TO_SEND) {
        val m = s"Message #${i}"
        println(s"Sending message: ${m}")
        val message = session.createTextMessage(m)
        producer.send(message)
        Thread.sleep(DELAY)
      }
      println("Active Thread Count: " + Thread.activeCount())
      println('B)
      producer.send(session.createTextMessage("END"))
      producer.close()
      session.close()
      connection.stop()
      connection.close()
      println('C)
      println("Active Thread Count: " + Thread.activeCount())

    } catch {
      case e: Throwable => e.printStackTrace()
    }

    println('D)
    println("Active Thread Count: " + Thread.activeCount())

  }

}
