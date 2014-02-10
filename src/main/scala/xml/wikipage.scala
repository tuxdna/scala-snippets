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
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.MediaWikiParserFactory
import de.tudarmstadt.ukp.wikipedia.parser.Link
/*
 * Using:
 * https://code.google.com/p/wikixmlj/ 
 * https://code.google.com/p/jwpl/wiki/JWPLParser
 */
object wikipage extends App {

  val markupParserFactory = new MediaWikiParserFactory()
  val markupParser = markupParserFactory.createParser()

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
            // "text" -> page.getText(),
            "categories" -> page.getCategories().toList.toString).toString)

        // val text = Option(page.getText()) match { case None => "" case Some(x) => x }
        val pars = markupParser.parse(page.getText())
        // only the links to other Wikipedia language editions
        val languages = Try { pars.getLanguages().toList } match {
          case Failure(t) => List() case Success(x) => x
        }

        //println(pars.getText())
        //println("tables")
        //pars.getParagraphs().foreach(p => println(p.getText))
        pars.getSections().foreach { section =>
          println(("    " * section.getLevel()) + "Section: " + section.getTitle())
          // section.getDefinitionLists().foreach(d => d.getDefinedTerm().toString())
          println(section.getText())
          section.getLinks(Link.`type`.INTERNAL).foreach { link =>
            println("  " + link.getTarget())
          }
          println()
          println()
        }
      }

    } match {
      case Success(d) => d
      case Failure(t) => t.printStackTrace()
    }
  }

  val wikidir = args(0)
  val location = new File(wikidir)
  for (s <- location.listFiles() // if s.getAbsolutePath().endsWith("25.xml")
      ) {
    println(s.getAbsolutePath())
    parseXml(s)
  }
}