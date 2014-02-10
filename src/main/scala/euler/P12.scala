package euler

object P12 extends App {

  def sumN(n: Int) = n * (n + 1) / 2
  def factorsCount2(n: Int) = {
    var idx = 0;
    for (i <- 2 to n / 2) {
      if (n % i == 0) {
        idx = idx + 1
      }
    }
    idx + 2
  }

  val max = (1 to 10000).par
    .map { i => (i, sumN(i)) }
    .map { x =>
      x match {
        case (a, tnum) => (a, tnum, factorsCount2(tnum))
      }
    }
    .filter { x =>
      x match {
        case (a, tnum, factCount) => {
          println((a, tnum, factCount))
          factCount >= 400
        }
      }
    }
    
    println(max);

}