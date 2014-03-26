package ch14

object ex07 {

  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(nodes: BinaryTree*) extends BinaryTree

  def treeSum(n: BinaryTree): Int = {
    n match {
      case Leaf(x) => x
      case Node(x @ _*) => x.map(k => treeSum(k)).sum
    }
  }

  def main(args: Array[String]): Unit = {
    val tree = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
    println(treeSum(tree))
  }
}