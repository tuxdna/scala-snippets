
val nums = new Array[Int](10)
val a = new Array[String](10)
val s = Array("Hello", "World")
s(0) = "Goodbye"

import scala.collection.mutable.ArrayBuffer

val b = ArrayBuffer[Int]() // new ArrayBuffer[Int]
b += 0 // append element
b += (1,2,3,5) // append elements
b ++= Array(8,34,22) // append collection
b.trimEnd(5) // remove elements from end
b.insert(2,6) // insert one value at postion 2
b.insert(2, -23, -44, -21, 32, -73, 75) // insert multiple values at position 2
b.remove(2) // remove one value
b.remove(2,1) // remove spedified number of values
b.toArray

for(e <- b) yield 2 * e
b map {2 * _}

val indexes = for(i <- 0 until b.length if b(i) < 0) yield i
for( j <- (1 until indexes.length).reverse ) b.remove(indexes(j))
println(b)
b.mkString("[", ",", "]")
