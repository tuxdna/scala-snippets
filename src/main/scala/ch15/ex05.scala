package ch15

import scala.io.Source

object ex05 {

  def readAllFromFile(path: String) = {
    val src = Source.fromFile(path)
    src.getLines.mkString("\n")
  }

  def main(args: Array[String]): Unit = {
    println(readAllFromFile("/etc/passwd"))
  }
}