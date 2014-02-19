package ch12

object ex10 extends App {

  def unless(condition: => Boolean)(block: => Unit) {
    if (!condition) {
      block
    }
  }

  var x = 10
  unless(x == 0) {
    x -= 1
    println(x)
  }

}
