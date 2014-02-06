package xml
import com.mongodb.casbah.MongoClient
import scala.io.Source
import scala.xml.pull._
import scala.collection.mutable.ArrayBuffer
import java.io.File
import java.io.FileOutputStream
import scala.xml.XML
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.commons.MongoDBObject

object importwiki extends App {

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

  def writePageToDB(buf: ArrayBuffer[String], collection: MongoCollection) = {
    val s = buf.mkString
    val x = XML.loadString(s)
    val pageId = (x \ "id")(0).child(0).toString
    println(pageId)
    val doc = MongoDBObject(
      "xml" -> s,
      "id" -> pageId)
    collection.insert(doc)
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
