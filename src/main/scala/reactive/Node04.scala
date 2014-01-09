package reactive
import scala.util.Success
import scala.util.Failure

object Node04 extends App {
  import Adventure._
  def block(i: Int) = {
    println("Iteration: " + i.toString)
    val adventure: Adventure = AdventureFactory()
    val treasure = for (
      c <- adventure.collectCoins;
      t <- adventure.buyTreasure(c)
    ) yield t

    treasure match {
      case Success(treasure) => println("Treasure: " + treasure.toString + " " + i.toString)
      case Failure(t) => println("Error Message: " + t.toString + " " + i.toString)
    }

  }
  (1 to 10 toList).foreach(i => block(i))
}
