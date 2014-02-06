package xml

import javax.xml.parsers.ParserConfigurationException
import org.apache.lucene.document.DateTools
import org.xml.sax.SAXException
import edu.jhu.nlp.wikipedia.WikiPage
import edu.jhu.nlp.wikipedia.WikiPageIterator
import edu.jhu.nlp.wikipedia.WikiXMLDOMParser
import edu.jhu.nlp.wikipedia.WikiXMLParser
import java.io.File
import java.io.FileInputStream
import java.io.DataInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import java.io.InputStream
import java.io.ByteArrayInputStream
import java.util.Vector
import scala.collection.JavaConversions._

object wikipage extends App {

  def parseXml(f: File) = {
    Try {
      val is: InputStream = new FileInputStream(f)
      val parser: WikiXMLParser = new WikiXMLDOMParser(is)
      parser.parse()
      val iterator: WikiPageIterator = parser.getIterator()
      val abstractTxt: StringBuffer = new StringBuffer();

      if (iterator.hasMorePages()) {
        val page: WikiPage = iterator.nextPage()
        page.isRedirect()
        page.isDisambiguationPage()
        page.isSpecialPage()
        page.isStub()
        val ib = Option(page.getInfoBox()) match {
          case None => ""
          case Some(x) => x.dumpRaw()
        }

        println(
          List(
            "id" -> page.getID(),
            "title" -> page.getTitle(),
            "infobox" -> ib,
            "isRedirect" -> page.isRedirect(),
            "isDisambiguationPage" -> page.isDisambiguationPage(),
            "isSpecialPage" -> page.isSpecialPage(),
            "isStub" -> page.isStub(),
            "text" -> page.getText(),
            "categories" -> page.getCategories().toList.toString).toString)
      }
    } match {
      case Success(d) => d
      case Failure(t) => t.printStackTrace()
    }
  }

  val location = new File(args(0))
  for (s <- location.listFiles()) {
    println(s.getAbsolutePath())
    parseXml(s);
  }
}