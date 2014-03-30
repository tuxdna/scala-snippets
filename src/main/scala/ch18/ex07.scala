package ch18

object ex07 {

  def func(obj: { def close(): Unit; def process() }) = {
    try {
      obj.process
    } catch {
      case t: Throwable => t.printStackTrace()
    } finally {
      obj.close
    }
  }

  class A {
    def close(): Unit = {
      println("A: Closing")
    }
    def process() = {
      println("A: Processing")
    }
  }

  class B {
    def close(): Unit = {
      println("B: Closing")
    }
    def process() = {
      println("B: Throwing exception")
      throw new RuntimeException
    }
  }

  def main(args: Array[String]): Unit = {

    val a = new A
    val b = new B

    func(a)
    func(b)

  }

}