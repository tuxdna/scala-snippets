package reactive

import math.random
import scala.language.postfixOps
import scala.util._
import control.NonFatal
import scala.util.{ Try, Success, Failure }
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

object Latency03 extends App {
  import MoreSockets._

  def block(i: Int) = {
    println("Iteration: " + i.toString)
    val socket = SocketFactory()
    val packet = socket.readFromMemory()

    Await.ready(packet, 1 second)
    packet onComplete {
      case Success(p) => {
        println("Packet Length: " + p.length.toString + " " + i.toString)
      }
      case Failure(t: Throwable) => {
        println("A Error message: " + t.getCause().toString + " " + i.toString)
      }
    }
    val confirmation =
      packet.flatMap(p => {
        socket.sendToAndBackup(p)
      })
    Await.ready(confirmation, 1 second)

    println("Confirmation Ready: " + confirmation.isCompleted.toString + " " + i.toString)
    confirmation onComplete {
      case Success(t) => {
        println("Received " + i.toString)
      }
      case Failure(t: Throwable) => {
        println("B Error message: " + t.getCause().toString + " " + i.toString)
      }
    }

  }
  (1 to 10 toList).foreach(i => block(i))
  blocking { Thread.sleep(3000) }

}