
import scala.util.control.Breaks._

val s = "Hello"
var sum = 0
breakable {
  for(i <- 0 until s.length) {
    if (i == 3) break;
    sum += s(i)
    println("Adding : "+i)
  }
}

printf("ascii sum of [%s] = %s\n", s, sum)

