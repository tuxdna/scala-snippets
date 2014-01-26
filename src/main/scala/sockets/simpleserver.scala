package sockets

import java.net.Socket
import java.net.ServerSocket
import java.net.InetAddress
import java.io.PrintStream

object simpleserver extends App {
  val port = 1090
  val serverSocket = new ServerSocket(port)

  println(serverSocket)

  def dealWithClient(client: Socket) = {
    val msg = "Hi"
    val ps = new PrintStream(client.getOutputStream())
    ps.println(client + ":" + msg)
    client.close()
  }

  while (!serverSocket.isClosed()) {
    val client = serverSocket.accept()
    println("Connected to: " + client)
    dealWithClient(client)
  }
}
