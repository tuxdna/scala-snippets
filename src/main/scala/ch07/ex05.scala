/*
 * 6. Write a program that copies all elements from a Java hash map into
 * a Scala hash map. Use imports to rename both classes.
 * 
 */

import java.util.{HashMap => JavaHashMap}
import scala.collection.mutable.{HashMap => ScalaHashMap}

object ex05 extends App {
  var javahm = new JavaHashMap[String, Int]()

  for( i <-  List("A" -> 1, "B" -> 2) ) javahm += (i._1, i._2)

  val scalahm = new ScalaHashMap[String, Int]()

  for((k,v) <- javahm) scalahm += (k -> v)
}
