package ch16

import scala.util.parsing.json.JSONFormat
import java.io._
import scala.xml.Text
object ex03 extends App {
  	val a = <li>Fred</li> match {
  	  case <li>{Text(t)}</li> => t
  	}

  	val b = <li>{"Fred"}</li> match {
  	  case <li>{t @ _}</li> => t
  	}
 
  	println(a)
  	println(b)
}