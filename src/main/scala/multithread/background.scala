package multithread

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object background extends App {

  // println("Welcome to spawn example")
  Future {
    println("This is a thread before sleep")
    Thread.sleep(1000)
    println("This is a thread after sleep")
  }
  
  println("We are done spawning")

}
