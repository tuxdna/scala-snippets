package ch16

import scala.xml.transform.RewriteRule
import scala.xml._
import scala.xml.transform.RuleTransformer

object ex09 extends App {

  val xml = <html>
              <head>
                <title> My list of jokes </title>
              </head>
              <body>
                <h1>This is my list of jokes </h1>
                <hr/>
                <img alt="my image" height="100" width="100"/>
                <img src="image1.jpg" alt="my image" height="100" width="100"/>
                <img src="image2.jpg" height="200" width="200"/>
                <img src="image3.jpg" height="100" width="100"/>
                <img src="image4.jpg" height="200" width="200"/>
              </body>
            </html>

  val f = (xml \\ "img") map { _.attribute("src") } filter {
    _ match {
      case Some(a) => true; case _ => false
    }
  } foreach (a => println(a.get))

  val rule1 = new RewriteRule {
    override def transform(n: Node) = n match {
      case e @ <img>{ _* }</img> => {
        val attr = e.attributes
        val m = attr.asAttrMap
        val newattr = m.++(Map("alt" -> "TODO"))
        //TODO
        // val attr2 = MetaData.apply(newattr)
        e.asInstanceOf[Elem].copy(e.prefix, e.label, attr)
      }
      case _ => n
    }
  }

  val transformed = new RuleTransformer(rule1).transform(xml)
  println(transformed)
}
