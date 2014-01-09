package reactive
import math.random

import scala.util.{ Try, Success, Failure }

object Node02 extends App {

  import Adventure._

  def block(i: Int) = {
    println("Iteration: " + i.toString)
    val adventure: Adventure = AdventureFactory()
    val tryCoins: Try[List[Coin]] = adventure.collectCoins()
    val tryTreasure: Try[Treasure] = tryCoins match {
      case Success(coins) => adventure.buyTreasure(coins)
      case Failure(t) => Failure(t)
    }

    tryTreasure match {
      case Success(treasure) => println("Treasure: " + treasure.toString + " " + i.toString)
      case Failure(t) => println("Error Message: " + t.toString + " " + i.toString)
    }
  }

  (1 to 10 toList).foreach(i => block(i))
}
