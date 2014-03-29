package ch15

object ex06 {

  def threadName = Thread.currentThread().getName()
  def log(s: String) = println(threadName + ": " + s)

  @volatile var flag: Boolean = false

  def main(args: Array[String]): Unit = {
    val setterThread = new Thread {
      override def run() {
        Thread.sleep(500)
        log("Setting to true")
        flag = true
        log("Done. Exiting.")
      }
    }

    val checkerThread = new Thread {
      override def run() {
        do {
          if (flag) log("Set")
          else { log("Wait..."); Thread.sleep(100) }
        } while (flag != true)
      }
    }
    checkerThread.start()
    setterThread.start()
    setterThread.join()
    checkerThread.join()

  }
}
