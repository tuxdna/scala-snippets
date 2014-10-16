package messaging.activemq

import javax.jms._
import org.apache.activemq.ActiveMQConnectionFactory
import scala.util.Random
import java.util.concurrent.CountDownLatch

object MessageConsumerMain {
  val BROKER_URL = "tcp://localhost:61616"
  val NON_TRANSACTED = false
  val NUM_MESSAGES_TO_SEND = 100
  val DELAY = 100
  def username = "admin"
  def password = "password"

  class Subscriber(latch: CountDownLatch) extends MessageListener {
    def onMessage(message: Message): Unit = {
      try {
        message match {
          case x: TextMessage =>
            val text = x.getText()
            text match {
              case "END" => {
                println("Received END message!")
                latch.countDown()
              }
              case _ => println("Received message:" + text)
            }
        }
      } catch {
        case e: JMSException =>
          System.out.println("Got a JMS Exception!");
        case _: Throwable =>
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println("""
Waiting to receive messages...
Either waiting for END message or press Ctrl+C to exit
""")

    val url = "tcp://localhost:61616" // BROKER_URL
    val connectionFactory = new ActiveMQConnectionFactory(username, password, url)
    val latch = new CountDownLatch(1)
    try {
      val connection = connectionFactory.createConnection()
      val clientId = System.getProperty("clientId")
      connection.setClientID(clientId)
      connection.start
      val session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE)
      val destination = session.createTopic("test-topic")
      val consumer = session.createConsumer(destination, clientId)
      consumer.setMessageListener(new Subscriber(latch));
      latch.await()
      consumer.close()
      session.close()
    } catch {
      case e: Throwable => e.printStackTrace()
    }
  }

}
