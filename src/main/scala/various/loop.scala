package various.loop

object test {

  for (i <- 1 to 3; j <- 1 to 3) {
    print("%s%s, ".format(i, j))
  }
  println()

  for (i <- 1 to 3; j <- 1 to 3; if i != j) {
    print("%s%s, ".format(i, j))
  }
  println()

}