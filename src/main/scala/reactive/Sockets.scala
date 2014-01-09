package reactive
import math.random
import scala.language.postfixOps
import scala.util._
import scala.util.{ Try, Success, Failure }
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

object Sockets {
  val EMail1 = (for { i <- 0 to 1 } yield (random * 256).toByte).toArray
  val EMail2 = (for { i <- 0 to 10 } yield (random * 256).toByte).toArray

  trait Socket {
    def readFromMemory(): Future[Array[Byte]]
    def sendToEurope(packet: Array[Byte]): Future[Array[Byte]]
  }

  def disconnect(a: Socket) = (random < 0.3)
  class InputException(msg: String) extends Error { override def toString = msg }
  class TransmissionException(msg: String) extends Error { override def toString = msg }
  val maxTotal = 50
  val Received = "received".map(x => x.toByte).toArray

  def packetSource(rand: Double, prob: Double): Array[Byte] = if (rand < prob) {
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
          throw (new InputException("Disconnected: Oooops"))
        else
          (1 to 10 toArray) flatMap (i => packetSource(random, 0.5))
      }

      def sendToEurope(packet: Array[Byte]): Future[Array[Byte]] = Future {
        if (packet.length > maxTotal)
          throw (new TransmissionException("Transmission Error: Nice try!"))
        else
          Received
      }
    }
  }
}