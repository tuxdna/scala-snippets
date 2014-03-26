package ch14

object ex06 {
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

  def treeSum(n: BinaryTree): Int = {
    n match {
      case Leaf(x) => x
      case Node(x, y) => treeSum(x) + treeSum(y)
    }
  }

  def main(args: Array[String]): Unit = {
    val tree = Node(Node(Node(Leaf(3), Leaf(8)), Leaf(2)), Leaf(5))
    println(treeSum(tree))
  }
}
