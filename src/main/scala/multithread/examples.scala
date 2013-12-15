package multithread

import java.util.concurrent.Callable

object examples extends App {

  // creates a new Thread right now?
  // NO!
  new Thread {
    println("Lets begin!")
    Thread.sleep(500) // wait half a seconds
    println("Inside the thread1")
  }

  val runnable1 = new Runnable {
    def run = {
      Thread.sleep(1000) // wait 1 second
      println("Inside the runnable1")
    }
  }

  val runnable2 = new Runnable {
    def run = {
      Thread.sleep(500) // wait 1 second
      println("Inside the runnable2")
    }
  }

  val thread1 = new Thread(runnable1)
  val thread2 = new Thread(runnable2)

  thread1.start()
  thread2.start()

  val callable = new Callable[Int] {
    def call: Int = { 10 }
  }

  callable.call()

}
