import java.net._
import java.io._

def process(url: URL) = {
  // do nothing
  url.openStream
}

try {
  process( new URL("http://horstmann.com/fred-tiny.gif") )
} catch {
  case e0: FileNotFoundException => println("Bad URL:",  e0 )
  case e1: MalformedURLException => println("Bad URL:",  e1 )
  case e2: IOException => e2.printStackTrace()
}
