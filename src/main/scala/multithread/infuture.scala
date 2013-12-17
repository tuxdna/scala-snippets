package multithread

import scala.util.Random
import java.util.concurrent.Executors
import scala.concurrent._
import java.util.concurrent.Callable

object infuture extends App {

  val random = new Random()
  class Searcher {
    def search(searchfor: String): String = {
      val waitTime = random.nextInt(1000)
      Thread.sleep(waitTime)
      s"hi -- it took ${waitTime} ms to process $searchfor at " +
        Thread.currentThread.getName()
    }
  }

  val searcher = new Searcher

  val poolSize = 4

  val pool = Executors.newFixedThreadPool(poolSize)

  val futures = (1 to 10).map { x =>
    {
      val callable = new Callable[String]() {
        def call(): String = {
          searcher.search("hello" + x);
        }
      }
      val future = pool.submit(callable)
      future
    }
  }

  println(searcher.search("hello"))

  for (future <- futures) {
    val result = future.get()
    println(result)
  }

  pool.shutdown()
}
