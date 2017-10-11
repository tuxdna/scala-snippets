package sealeadthings

sealed trait Transformer[P, Q] {
  protected def transform(x: P): Q
  def apply(x: P): Q = transform(x)
}

object Int2StringTransformer extends Transformer[Int, String] {
  def transform(x: Int): String = x.toString
}

object List2SetTransformer extends Transformer[List[_], Set[_]] {
  def transform(x: List[_]) = x.toSet
}

object Example {
  def main(args: Array[String]) {
    println(List2SetTransformer(List(10, 11)))
  }
}
