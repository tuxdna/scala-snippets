/*
 * 9. Write a Scala program that counts how many files with .class
 * extension are in a given directory and its subdirectories.
 */

import java.io.File

object ex09 extends App {
  def subdirs(dir: File): Iterator[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(subdirs _)
  }
  if (args.length < 1) {
    println("No input from the command line. Exiting.")
    sys.exit(-1)
  }
  var path = args(0)
  val dir = new File(path)
  println("Searching for .class files in: "+dir.getAbsolutePath)
  for(d <- (subdirs(dir) ++ Array(dir) )) {
    for(f <- d.listFiles.filter(_.getAbsolutePath.endsWith(".class")) )  println(f)
  }
}
