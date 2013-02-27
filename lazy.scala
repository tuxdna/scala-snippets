
lazy val words = scala.io.Source.fromFile("/proc/cpuinfo").mkString

println(words)
