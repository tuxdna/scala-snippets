package ch11

object ex08 extends App {

  class Matrix(val rows: Int, val cols: Int) {
    private val mat: Array[Array[Double]] = Array.ofDim[Double](rows, cols)
    def +(that: Matrix) = {
      if (this.rows == that.rows && this.cols == that.cols) {
        val rs = Matrix(this.rows, this.cols)
        for (r <- 0 until mat.length; c <- 0 until mat(r).length) {
          rs(r, c) = this(r, c) + that(r, c)
        }
        rs
      } else {
        throw new Error("Incompatible dimensions")
      }
    }

    def *(that: Matrix) = {
      //TODO: Complete this
      if (this.cols == that.rows) {
        val rs = Matrix(this.rows, that.cols)
        for (r <- 0 until mat.length) {
          for (c <- 0 until mat(r).length) {
            rs(r, c) = this(r, c) + that(r, c)
          }
        }
        rs
      } else {
        throw new Error("Incompatible dimensions")
      }
    }

    def apply(x: Int, y: Int) = {
      mat(x)(y)
    }

    def update(x: Int, y: Int, v: Double) = {
      mat(x)(y) = v
    }
    override def toString = {
      mat.map(r => r.mkString(" ")).mkString("\n")
    }
  }

  object Matrix {
    def apply(r: Int, c: Int) = new Matrix(r, c)
    def apply(a: Array[Double]) = {
      val m = new Matrix(1, a.length)
      (0 until a.length).zip(a).foreach { x =>
        m(0, x._1) = x._2
      }
      m
    }

    def apply(arr: Iterable[Double]) = {
      val a = arr.toList
      val m = new Matrix(1, a.length)
      (0 until a.length).zip(a).foreach { x =>
        m(0, x._1) = x._2
      }
      m
    }
  }

  type T = Int
  val arr = Array.ofDim[T](100, 60)

  val m1 = Matrix(3, 4)
  val m2 = Matrix(3, 4)
  println(m1(0, 0))
  m1(0, 0) = 10
  println(m1(0, 0))

  val m3 = Matrix(Array(10.0, 11, 23))
  println(m3)
  val m4 = Matrix(Array(9.0, 0.1, 0.3))
  println(m4)
  val m5 = m3 + m4
  println(m5)
}
