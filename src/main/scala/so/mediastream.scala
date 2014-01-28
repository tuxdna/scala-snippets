package so

import java.net.URL
import java.net.Socket
import java.net.InetSocketAddress
import java.io.BufferedReader
import java.io.InputStreamReader

object mediastream extends App {
  def socketurl = {
    val timeout = 2000
    val host = "localhost" // "70.36.96.24"
    val port = 13384
    val socket = new Socket
    val endPoint = new InetSocketAddress(host, port)
    socket.connect(endPoint, timeout);
    val (in, out) = (socket.getInputStream(), socket.getOutputStream())
    val httpRequest = "GET /;stream.mp3 HTTP/1.1\r\n\r\n"
    val command = httpRequest.getBytes()
    println(httpRequest)
    out.write(command)

    var buf = Vector(0, 0, 0, 0)
    var c = in.read()
    var cont = true
    while (c != -1 && cont) {
      buf = buf.drop(1) :+ c
      print(c.toChar)
      if (buf(0) == '\r' && buf(1) == '\n' && buf(2) == '\r' && buf(3) == '\n') {
        cont = false
        println("Its a match")
      }
      c = in.read()
    }

    while (c != -1) {
      // keep buffering the data from here on!
    }
  }
  socketurl

}