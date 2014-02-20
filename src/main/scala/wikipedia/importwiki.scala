package wikipedia
import com.mongodb.casbah.MongoClient
import scala.io.Source
import scala.xml.pull._
import scala.collection.mutable.ArrayBuffer
import java.io.File
import scala.xml.XML
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.commons.MongoDBObject
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.MediaWikiParserFactory
import java.io.ByteArrayInputStream
import edu.jhu.nlp.wikipedia.WikiXMLParser
import edu.jhu.nlp.wikipedia.WikiXMLDOMParser
import edu.jhu.nlp.wikipedia.WikiPageIterator
import edu.jhu.nlp.wikipedia.WikiPage

import scala.collection.JavaConversions._

object importwiki extends App {
  //[success] Total time: 7457 s, completed Feb 7, 2014 2:21:30 AM

  object Config {
    val dbName = "wikipedia01"
    val collectionName = "pages"
    val dbHost = "localhost"
    val dbPort = 27017
  }

  def getCollection = {
    val mongoClient = MongoClient(Config.dbHost, Config.dbPort)
    val db = mongoClient(Config.dbName)
    db(Config.collectionName)
  }

  // db.pages.ensureIndex({isDisambiguationPage:1, isRedirect: 1, isSpecialPage: 1, isStub: 1, id: 1}) 
  val markupParserFactory = new MediaWikiParserFactory()
  val markupParser = markupParserFactory.createParser()
  def decode(xml: String): Map[String, Any] = {
    val stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
    val parser: WikiXMLParser = new WikiXMLDOMParser(stream)
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
        case Some(x) => x.dumpRaw() case None => ""
      }
      val wikiText = page.getText()
      val pars = markupParser.parse(wikiText)
      val plainText = pars.getSections().toList.map { section =>
        ("    " * section.getLevel()) +
          Option(section.getTitle()).getOrElse("") + "\n" +
          section.getText() + "\n\n"
      }.mkString

      val m = Map(
        "xml" -> xml,
        "id" -> page.getID(),
        "title" -> page.getTitle(),
        "infobox" -> ib,
        "isRedirect" -> page.isRedirect(),
        "isDisambiguationPage" -> page.isDisambiguationPage(),
        "isSpecialPage" -> page.isSpecialPage(),
        "isStub" -> page.isStub(),
        "categories" -> page.getCategories().toList,
        "plaintext" -> plainText,
        "wikitext" -> wikiText)

      m
    } else {
      Map()
    }
  }

  def writePageToDB(buf: ArrayBuffer[String], collection: MongoCollection) = {
    val xml = buf.mkString
    val attributes = decode(xml)
    val uo = MongoDBObject.newBuilder
    uo ++= attributes
    val doc = uo.result
    attributes.get("id") match {
      case Some(id) =>
        println(id + ": " + attributes.get("title"))
        collection.insert(doc)
      case None => // ignore
    }
  }

  def process(xmlFile: File, collection: MongoCollection) = {
    val xml = new XMLEventReader(Source.fromFile(xmlFile))

    var insidePage = false
    var buf = ArrayBuffer[String]()
    for (event <- xml) {
      event match {
        case EvElemStart(_, "page", _, _) => {
          insidePage = true
          val tag = "<page>"
          buf += tag
        }
        case EvElemEnd(_, "page") => {
          val tag = "</page>"
          buf += tag
          insidePage = false

          writePageToDB(buf, collection)
          buf.clear
        }
        case e @ EvElemStart(_, tag, _, _) => {
          if (insidePage) {
            buf += ("<" + tag + ">")
          }
        }
        case e @ EvElemEnd(_, tag) => {
          if (insidePage) {
            buf += ("</" + tag + ">")
          }
        }
        case EvText(t) => {
          if (insidePage) {
            buf += (t)
          }
        }
        case _ => // ignore
      }
    }
  }

  val xmlLocation = args(0)
  val xmlFile = new File(xmlLocation)
  val coll = getCollection
  process(xmlFile, coll)
}
