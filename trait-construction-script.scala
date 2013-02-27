trait T1 {
  println(" in T1 : x = " + x )
  val x = 1
  println(" in T1 : x = " + x )
}

trait T2 {
  println(" in T2 : y = " + y )
  val y = 1
  println(" in T2 : y = " + y )
}

class Base12 {
  println(" in Base12 : b = " + b )
  val b = 1
  println(" in Base12 : b = " + b )
}

class C12 extends Base12 with T1 with T2 {
  println(" in C12 : c = " + c )
  val c = 1
  println(" in C12 : c = " + c )
}
  

println("Creating C12:")
new C12
println("After Creating C12")
