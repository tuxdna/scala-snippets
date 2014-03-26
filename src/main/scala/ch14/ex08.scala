package ch14

object ex08 {

  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(op: Char, nodes: BinaryTree*) extends BinaryTree

  def evaluate(n: BinaryTree): Int = {
    n match {
      case Leaf(x) => x
      case Node(op, x ) => op match {
        case '-' => - evaluate(x)
      }
      case Node(op, x @ _*) => {
        val s = x.map(k => evaluate(k))
        op match {
          case '+' => s.sum
          case '*' => s.product
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val tree = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5)))
    println(evaluate(tree))
  }

}