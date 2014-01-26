package classntrait

object objectsntypes extends App {
  trait A
  trait B
  trait AB extends A with B
  class C extends A with B

  type TypeAB = A with B

  println(classOf[A])
  println(classOf[B])
  println(classOf[AB] )
  println(classOf[C])
  // println(TypeAB)
  // classOf[TypeAB]
  
  val c = new C
  c match {
    case _: TypeAB => 
  }
}