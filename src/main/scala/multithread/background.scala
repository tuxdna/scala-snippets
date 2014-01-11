package multithread

import scala.concurrent.ops._

object background extends App {

  // println("Welcome to spawn example")
  spawn {
    println("This is a thread before sleep")
    Thread.sleep(1000)
    println("This is a thread after sleep")
  }
  
  println("We are done spawning")

}
