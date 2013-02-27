val list1 = List(1,3,4,5)
val list2 = List(12,34,53)
val empty = List()

for( l <- List(list1, list2, empty) ) {
  l match {
    case List(_,3,_,_) => println("Second element is 3")
    case List(_*) => println("A list with 0 or more elements")
  }
}
