package sockets

import java.net.Socket
import java.io.PrintStream
import java.util.Scanner

object simpleclient extends App {
  val s = new Socket("localhost", 1090)
  val ps = new PrintStream(s.getOutputStream())
  ps.write("".getBytes())
  val sc = new Scanner(s.getInputStream())
  while (sc.hasNext()) {
    print(sc.next())
  }
}
