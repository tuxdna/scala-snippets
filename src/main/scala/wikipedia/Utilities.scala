package wikipedia
import com.mongodb.casbah.MongoClient
object Utilities {
  def getCollection = {
    val mongoClient = MongoClient(Config.dbHost, Config.dbPort)
    val db = mongoClient(Config.dbName)
    db(Config.collectionName)
  }

}