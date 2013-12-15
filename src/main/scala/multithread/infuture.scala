package multithread

import scala.parallel.Future
import scala.actors.threadpool.ExecutorService
import scala.actors.threadpool.Executors
import scala.actors.threadpool.Callable
import scala.util.Random

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

  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)

  val futures = (1 to 10).map { x =>
    {
      val callable = new Callable() {
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

}
