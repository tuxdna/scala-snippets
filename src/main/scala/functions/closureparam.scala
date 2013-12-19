package functions

object closureparam extends App {
  class MyDb {
    private def beginTx() = println("Begin Transaction")
    private def endTx() = println("End Transaction")

    // Notice how we declare the function parameter f
    // as a call-by name expression that returns a Unit
    def withinTransaction(f: => Unit) = { beginTx; f; endTx }
  }

  val db = new MyDb

  println("First Set of Operations")
  db.withinTransaction {
    println("Operation1"); println("Operation2")
  }
  println
  println("Next Set of Operations")
  db.withinTransaction {
    println("Operation3"); println("Operation4"); println("Operation5")
  }
}