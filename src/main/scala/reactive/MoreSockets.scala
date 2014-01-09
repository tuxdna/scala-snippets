package reactive
import math.random
import scala.language.postfixOps
import scala.util._
import control.NonFatal
import scala.util.{ Try, Success, Failure }
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

object MoreSockets {
  val EMail1 = (for { i <- 0 to 1 } yield (random * 256).toByte).toArray

  val EMail2 = (for { i <- 0 to 10 } yield (random * 256).toByte).toArray

  type URL = String

  trait Socket {
    def readFromMemory(): Future[Array[Byte]]
    def sendTo(url: URL, packet: Array[Byte]): Future[Array[Byte]]
    def sendToAndBackup(packet: Array[Byte]): Future[(Array[Byte], Array[Byte])]
  }

  def disconnect(a: Socket) = (random < 0.1)
  class InputException(msg: String) extends Error {
    override def toString = msg
  }
  class TransmissionException(msg: String) extends Error {
    override def toString = msg
  }
  object mailServer {
    val europe = "http://example.de"
    val usa = "http://example.com"
  }
  val maxTotalEurope = 70
  val multiplierUSA = 4

  val Received = "received".map(x => x.toByte).toArray

  def packetSource(rand: Double, prob: Double): Array[Byte] =
    if (rand < prob) {
      blocking { Thread.sleep(10) }
      EMail2
    } else {
      blocking { Thread.sleep(1) }
      EMail1
    }

  object SocketFactory {
    def apply() = new Socket {
      def readFromMemory(): Future[Array[Byte]] = Future {
        if (disconnect(this))
          throw (new InputException("Oooops"))
        else (1 to 10 toArray) flatMap (i => packetSource(random, 0.5))
      }

      def sendTo(url: URL, packet: Array[Byte]): Future[Array[Byte]] = Future {
        url match {
          case "http://example.de" =>
            if (packet.length > maxTotalEurope)
              throw (new TransmissionException("Guter Versuch!"))
            else
              Received
          case "http://example.com" =>
            if (packet.length % multiplierUSA == 0)
              throw (new TransmissionException("Nice try!"))
            else
              Received
        }
      }
      def sendToAndBackup(packet: Array[Byte]): Future[(Array[Byte], Array[Byte])] = {
        val europeConfirm = sendTo(mailServer.europe, packet)
        val usaConfirm = sendTo(mailServer.usa, packet)
        europeConfirm.zip(usaConfirm)
      }

      def sendToSafe(packet: Array[Byte]): Future[Array[Byte]] = {
        sendTo(mailServer.europe, packet) recoverWith {
          case europeError => sendTo(mailServer.usa, packet) recover {
            //            case usaError =>
            //              usaError.getCause().toString.map(x => x.toByte).toArray
            case usaError =>
              usaError.getMessage.map(x => x.toByte).toArray
          }
        }
      }
    }
  }
}