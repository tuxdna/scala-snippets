package ch11

object ex01 extends App {
  val v1 = 3 + 4 -> 5
  // val v2 = 3 -> 4 + 5 // error: found   : Int(5)  required: String
  val a0 = 3 -> 4
  // val a1 = a0 + 5 // error: because of implicit any2String which converts a0 into string
  // but we want 3 -> (4 + 5)
  val v2 = 3 -> (4 + 5)
  
  println(v1, a0, v2)
}