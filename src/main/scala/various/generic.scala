object Generics {

  type UnaryOp[T, R] = T => R
  def run[R]( gen: UnaryOp[Int,R] ) {
    for(x <- 1 to 5) println(gen(x))
  }

  class Reference[T] {
    private var contents: T = _  // represents a default value for T
    def set(value: T) { contents = value }
    def get: T = contents
  }


  def main(args: Array[String]) {
    run( x => -x )
    run( x => Array.fill(x)("*").mkString )

    val cell = new Reference[Int]
    cell.set(13)
    println("Reference contains the half of " + (cell.get * 2 ))
  }

}
