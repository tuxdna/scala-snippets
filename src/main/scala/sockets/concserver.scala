package sockets

import java.net.Socket
import java.net.ServerSocket
import java.net.InetAddress
import java.io.PrintStream
import java.util.Scanner
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.Date
import scala.util.{ Try, Success, Failure }
import scala.util.Random

object concserver extends App {

  def dealWithClient(client: Socket) = {
    val f = Future {
      /*
     * Wait for input with a timeout.
     * Select response based in input.
     * Deliver response.
     */

      Try {
        val msg = "Hi"
        println(client + "Waiting for input from client")
        val sc = new Scanner(client.getInputStream())
        val ps = new PrintStream(client.getOutputStream())
        while (true) {
          // read message
          val timeout = Math.abs(10 * (Random.nextInt % 10))
          client.setSoTimeout(timeout)
          ps.println("Welcome: " + client)
          val msgin = sc.nextLine.trim
          client.setSoTimeout(0) // clear the timeout

          // select response
          val msgout = msgin match {
            case "time" => { val d = new Date; d.toString() }
            case _ => msgin
          }

          // deliver response
          ps.println(msgout)
        }
        
        println(client + "closing connection")
        client.close()
      }
    }

    // failure handling
    f map {
      case Success(msg) => {
        println(client + "Message sent to client: " + msg)
        msg
      }
      case Failure(t) => {
        client.close()
        t match {
          case x: NoSuchElementException => println("Timeout was reached")
          case _ => t.printStackTrace()
        }
      }
    }

  }

  val port = 1090
  val serverSocket = new ServerSocket(port)
  println("Listening on: " + serverSocket)
  while (!serverSocket.isClosed()) {
    println("Waiting for new connection")
    val client = serverSocket.accept()
    println("Connected to: " + client)
    dealWithClient(client)
  }
}

