package ch05

// 1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless Methods,” on page 51 so that it doesn’t turn negative at Int.MaxValue.

object ex01 extends App {
  class Counter {
    private var value = 0 // You must initialize the field
    def increment() { 
       value = if(value == Int.MaxValue) 0 else value + 1
    }
    def current() = value
  }

  // lets now check it
  val c = new Counter
  for(i <- 0 to Int.MaxValue-1) {
    c.increment
    if(c.current < 0) println("Error!!")
  }
  for(i <- 0 to Int.MaxValue-1) {
    c.increment
    if(c.current < 0) println("Error!!")
  }
  println("Super!")
}
