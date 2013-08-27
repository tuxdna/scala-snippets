package various.lazyval

object test {

  lazy val words = scala.io.Source.fromFile("/proc/cpuinfo").mkString

  println(words)
}