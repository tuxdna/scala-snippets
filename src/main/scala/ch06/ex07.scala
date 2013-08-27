// 7. Implement a function that checks whether a card suit value from the
// preceding exercise is red.

object ex07 extends App {

  object Color extends Enumeration {
   val RED = Value
   val BLACK = Value
  }

  object Card extends Enumeration {
    type Card = Value
    val CLUB    = Value("♣")
    val DIAMOND = Value("♦")
    val HEART   = Value("♥")
    val SPADE   = Value("♠")
  }

  import Card._
  def isRed(c: Card): Boolean = {
    Array(HEART,DIAMOND) contains c
  }

  for(c <- Card.values) println(c.id, c, isRed(c))
}
