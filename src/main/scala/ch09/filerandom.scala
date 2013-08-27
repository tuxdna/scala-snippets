package ch09


object filerandom extends App {
  val r = new java.util.Random
  val numItems = 10 + r.nextInt(10)
  println("Generating %s number...".format(numItems))
  for(i <- 0 to numItems) {
    val n = r.nextDouble
    println(n)
  }
}
