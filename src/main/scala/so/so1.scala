package so

import scala.util.Random

object so1 extends App {

  class A(x: Int) {
    val s = "*" * x
    def doSomething() = {}
  }

  (1 to 100).foreach(i => {
    // System.gc()
    val MB = 1024 * 1024
    val size = 36 //30+Random.nextInt(10)
    println(i + " -> Allocating " + size + " MB")
    val a = new A(size * MB)
    a.doSomething()
  })
}
