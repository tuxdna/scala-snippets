package ch14

object ex10 {

  def main(args: Array[String]): Unit = {
    {
      def f(x: Double) = if (x > 0) Some(math.sqrt(x)) else None
      def g(x: Double) = if (x != 1) Some(1 / (x - 1)) else None

      def compose(f: (Double) => Option[Double], g: (Double) => Option[Double])(x: Double) = {
        val a = f(x)
        val b = g(x)
        if (a == None || b == None) None else Some(a, b)
      }

      val h = compose(f, g)(_)
      println(h(2))
      println(h(1))
      println(h(0))
    }
    
    // correct method
    {
      type T = Double => Option[Double]

      def f(x: Double) = if (x > 0) Some(math.sqrt(x)) else None
      def g(x: Double) = if (x != 1) Some(1 / (x - 1)) else None

      def compose(f: T, g: T): T = {
        (x: Double) =>
          f(x) match {
            case Some(x) => g(x)
            case None => None
          }
      }
      
      val h = compose(f, g)
      println(h(2))
      println(h(1))
      println(h(0))
    }

  }

}