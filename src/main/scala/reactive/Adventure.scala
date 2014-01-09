package reactive

import math.random
import scala.util.Try

object Adventure {
  abstract class Coin { val denomination: Int }
  case class Silver() extends Coin { val denomination = 1 }
  case class Gold() extends Coin { val denomination = 10 }
  abstract class Treasure { val value: Int }
  trait Adventure {
    def collectCoins(): Try[List[Coin]]
    def buyTreasure(coins: List[Coin]): Try[Treasure]
  }
  def eatenByMonster(a: Adventure) = (random < 0.3)
  class GameOverException(msg: String) extends Error { override def toString = msg }
  val treasureCost = 50

  object Diamond extends Treasure {
    val value = treasureCost
    override def toString = "Diamond"
  }

  def coinSource(rand: Double, prob: Double): Coin = if (rand < prob) {
    Thread.sleep(100)
    new Gold
  } else {
    Thread.sleep(10)
    new Silver
  }

  object AdventureFactory {
    def apply() = new Adventure {
      def collectCoins(): Try[List[Coin]] = Try {
        if (eatenByMonster(this))
          throw (new GameOverException("Oooops"))
        else for { i <- 1 to 10 toList } yield coinSource(random, 0.5)
      }
      def totalCoins(coins: List[Coin]) =
        coins.foldLeft(0)((sum, coin) => sum + coin.denomination)

      def buyTreasure(coins: List[Coin]): Try[Treasure] = Try {
        if (totalCoins(coins) < treasureCost)
          throw (new GameOverException("Nice try!"))
        else
          Diamond
      }
    }
  }
}