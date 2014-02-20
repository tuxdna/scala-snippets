package wikipedia

import scala.xml.pull._
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import com.mongodb.DBObject
import edu.jhu.nlp.wikipedia.WikiXMLDOMParser
import edu.jhu.nlp.wikipedia.WikiXMLParser
import edu.jhu.nlp.wikipedia.WikiPageIterator
import edu.jhu.nlp.wikipedia.WikiPage
import scala.collection.JavaConversions._
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.MediaWikiParserFactory
import de.tudarmstadt.ukp.wikipedia.parser.Link
import com.mongodb.casbah.commons.MongoDBObject
import java.io.ByteArrayInputStream

object expandwiki extends App {
  // db.pages.ensureIndex({isDisambiguationPage:1, isRedirect: 1, isSpecialPage: 1, isStub: 1, id: 1}) 
  val markupParserFactory = new MediaWikiParserFactory()
  val markupParser = markupParserFactory.createParser()
  def decode(obj: DBObject): Map[String, Any] = {
    val xml: String = obj.get("xml").toString()
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

      val m = Map("id" -> page.getID(),
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

  val coll = Utilities.getCollection
  for (x <- coll.find()) {
    val xml = x.get("xml")
    val id = x.get("id")
    println(id + " => " + x.containsKey("title"))

    // title
    // wikitext
    // plaintext
    // categories
    Try(decode(x)) match {
      case Failure(t) => t.printStackTrace()
      case Success(a) => // update a
        println("adding attributes : _id -> " + x.get("_id"))
        val uo = MongoDBObject.newBuilder
        uo.++=(a)
        val update = MongoDBObject("$set" -> uo.result)
        coll.update(MongoDBObject("_id" -> x.get("_id")), update)
    }
  }
}
