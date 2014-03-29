package ch15

object ex08 {
  def allDifferent[@specialized(Long, Double) T](x: T, y: T, z: T) = {
    x
  }

  def main(args: Array[String]): Unit = {
    println("@specialize example. use javap -c /path/to/classes/ch15/ex08*.class")
  }

}
