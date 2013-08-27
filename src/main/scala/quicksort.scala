object qs1 {
  def sort(xs: Array[Int]) {

    def swap(i: Int, j: Int) {
      val t = xs(i); xs(i) = xs(j); xs(j) = t
    }

    def sort1(l: Int, r: Int) {
      val pivot = xs((l+r) / 2)
      var i = l; var j = r
      while(i<=j) {
	while(xs(i) < pivot) i += 1
	while(xs(j) > pivot) j -= 1
	if(i<=j) {
	  swap(i,j)
	  i += 1
	  j -= 1
	}
      }
      if(l < j) sort1(l, j)
      if(j < r) sort1(i, r)
    }
    
    sort1(0, xs.length - 1)
  }


  def main(args: Array[String]) {
    val k = Array(3,4,1,66,12)
    sort(k)
    k.foreach(println)
  }
}

object qs2 {
  def sort(xs: Array[Int]): Array[Int] =  {
    if(xs.length <= 1) xs
    else {
      val pivot = xs( xs.length / 2 )
      Array.concat(
	sort(xs filter (pivot >)),
	xs filter (pivot ==),
	sort(xs filter (pivot <))
      )
    }
  }


  def main(args: Array[String]) {
    var k = Array(3,4,1,66,12)
    k = sort(k)
    k.foreach(println)
  }
}
