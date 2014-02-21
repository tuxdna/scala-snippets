package functions

import scala.util.Random

object sort {
  def quickSort(list: List[Int]): List[Int] = {
    if (list.length > 1) {
      // find pivot
      val mid = list.length / 2
      val pivot = list(mid)

      // partition
      val (lesserEqual, greater) = (list.take(mid) ++ list.drop(mid + 1)).partition(x => x <= pivot)

      // sort recursively
      quickSort(lesserEqual) ++ List(pivot) ++ quickSort(greater)
    } else list
  }

  def main(args: Array[String]) {
    val lst = List(5, 3, 1, 6, 8)
    println(quickSort(lst))
    val rnd = quickSort((1 to 1000).map(x => Random.nextInt(10000)).toList)
    println(rnd)
  }
}
