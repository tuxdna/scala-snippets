// 6. Write an enumeration describing the four playing card suits so that
// the toString method returns ♣, ♦, ♥, or ♠.

object ex06 extends App {

  object Card extends Enumeration {
    val CLUB    = Value("♣")
    val DIAMOND = Value("♦")
    val HEART   = Value("♥")
    val SPADE   = Value("♠")
  }

  for(c <- Card.values) println(c.id, c)
}
