package reactive

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{ Try, Success, Failure }

object Latency02 extends App {
  import Sockets._

  def block(i: Int) = {
    println("Iteration: " + i.toString)
    val socket = SocketFactory()
    val packet = socket.readFromMemory()
    packet onComplete {
      case Success(p) => { println("Packet Read: " + i.toString) }
      case Failure(t: Throwable) => println(i + " A Error message: " + t.getCause())
    }

    val confirmation = packet.flatMap { p => socket.sendToEurope(p) }
    println(i + " Confirmation Ready: " + confirmation.isCompleted)
    confirmation onComplete {
      case Success(cf) => { println(i + " Transfer SUCCESS!!") }
      case Failure(t: Throwable) => println(i + " B Error message: " + t.getCause())
    }
  }

  (1 to 8 toList).foreach(i => block(i))

  blocking { Thread.sleep(3000) }
}