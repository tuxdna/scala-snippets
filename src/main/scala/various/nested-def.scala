package various.nesteddef

object test {

  def countToN(n: Int) {
    def count(i: Int) {
      if (i <= n) {
        println(i)
        count(i + 1)
      }
    }
    count(1)
  }

  countToN(5)

}