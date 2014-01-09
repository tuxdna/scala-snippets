package reactive
import math.random

object Node03 extends App {

  abstract class Try[+T] {
    def flatMap[S](f: T => Try[S]): Try[S] = {
      this match {
        case Success(x) => f(x)
        case f @ Failure(x) => f
      }
    }
  }
  case class Success[+T](x: T) extends Try[T]
  case class Failure(t: Throwable) extends Try[Nothing]
  object Try {
    def apply[T](r: => T): Try[T] = try {
      Success(r)
    } catch {
      case t: Throwable => Failure(t)
    }
  }

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
    /* The anonymous class syntax is used for this factory object,
     * allowing us to instantiate an object
     * that extends a trait having undefined members.
     * The anonymous class must provide definitions
     * for all undefined members of the trait.
     * Note that this object has an apply method:
     * AdventureFactory() is desugared to
     * AdventureFactory.apply()
     */
    def apply() = new Adventure {
      def collectCoins(): Try[List[Coin]] = Try {
        if (eatenByMonster(this))
          throw (new GameOverException("Monster 8 you"))
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
  /* Exception handling with an explicit case match
   * to Success or Failure.
   */
  def block(i: Int) = {
    println("Iteration: " + i.toString)
    val adventure: Adventure = AdventureFactory()
    val c = adventure.collectCoins;
    val t = c.flatMap(adventure.buyTreasure)
    println("Treasure: " + t.toString + " " + i.toString)
  }

  /* Multiple executions of a block of commands where
   * each block contains one collectCoins and
   * one buyTreasure. If either call fails, the whole iteration does not fail,
   * because we are catching exceptions in this implementation.
   * Note that these blocks execute synchrounsly.
   */
  (1 to 10 toList).foreach(i => block(i))
}
