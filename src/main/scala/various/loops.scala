package various.loops
object test {

  // permutation
  for (i <- 1 to 3; j <- 1 to 3) print("%s%s, ".format(i, j))
  println()

  // ! both same
  for (i <- 1 to 3; j <- 1 to 3 if i != j) print("%s%s, ".format(i, j))
  println()

  // loop variable introduction
  for (i <- 1 to 3; from = 4 - i; j <- from to 3) print("%s%s, ".format(i, j))
  println()

  val k = for (i <- 1 to 10) yield i
  println(k)

  // simple for comprehension
  val k1 = for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar
  println(k1)

}