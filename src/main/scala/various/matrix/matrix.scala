package various.matrix

class Matrix[T: Numeric](rows: Int, cols: Int) {
  private val mat: Array[Array[Double]] = Array.ofDim(rows, cols)
  // def + (other: Matrix[T]) = {
  //   for(val r <- 0 until _mat.length;
  // 	  val c <- 0 until _mat(r).length) println(_mat(r)(c))
  // }
  def hello = Unit
  def apply(x: Int, y: Int) = {
    hello
    mat(x)(y)
  }
  def update(x: Int, y: Int, v: Double) = mat(x)(y) = v
}

object matrix extends App {
  val m1 = new Matrix[Double](3, 4)
  // val m2 = Matrix(Array(10,11,23))
  println(m1(0, 0))
  m1(0, 0) = 10
  println(m1(0, 0))
}

// object Matrix {
//   // def apply(a: Array[Double]) = {
//   //   // hello
//   //   println("Hello")
//   //   val m = new Matrix
//   // }

//   def apply(a: Iterable[Double]) = {
//     // hello
//     println("Hello")
//     val m = new Matrix
//   }
//   // def update(x: Int, y: Int, v: Double ) = mat(x)(y) = v
// }

