val sundries = List(23, "Hello", 8.5, 'q')

for(sundry <- sundries) {
  sundry match {
    case i: Int => println("We got an integer: "+i)
    case s: String => println("We got a string: "+s)
    case d: Double => println("We got a double: "+d)
    case other => println("We got something else: "+other)
  }
}
