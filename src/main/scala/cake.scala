object OldPrime {
  def unapply(x: Int) = if( (2 until x).forall(x % _ != 0)) Some(x) else None
}

trait Mathematics {
  type Number

  trait PrimeExtractor {
    def unapply(x: Number): Option[Number]
  }

  val Prime: PrimeExtractor
}

trait FastIntegerMath extends Mathematics {
  type Number = Int

  object Prime extends PrimeExtractor {
    def unapply(x: Int): Option[Int] = {
      if( (2 to math.sqrt(x).toInt).forall(x % _ != 0) ) Some(x) else None
    }
  }
}

object Cake extends App with FastIntegerMath {
  (1 to 10).foreach { i =>
    i match {
      case OldPrime(x) => println(i + " is prime")
      case _ => println(i + " is NOT prime ")
   }
  }

  (1 to 10).foreach { i =>
    i match {
      case Prime(x) => println(i + " is prime ")
      case _ => println(i + " is NOT prime ")
    }
  }
}
