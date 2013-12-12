object control extends App {
  def runInThread(block: () => Unit) {
    new Thread() {
      override def run() {
        block()
      }
    }.start
  }

  def perform = {
    println("invoking runInThread()")
    runInThread { () => println("Hi"); Thread.sleep(500); println("Bye") }
  }

  def runInThread2(block: => Unit) {
    new Thread() {
      override def run() {
        block
      }
    }.start
  }

  def perform2 = {
    println("invoking runInThread2()")
    runInThread2 {
      println("Hi2")
      Thread.sleep(200)
      println("Bye2")
    }
  }

  def until(condition: => Boolean)(block: => Unit) {
    if (!condition) {
      block
      until(condition)(block)
    }
  }

  perform
  perform2

  var x = 10
  until(x == 0) {
    x -= 1
    print(x + " ")
  }
  
}
