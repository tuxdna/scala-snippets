package wikipedia

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import scala.util.Try
import scala.util.Failure
import scala.util.Success
import java.io.File
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.index.IndexReader
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.util.Version
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field

object indexdocs extends App {
  def help() {
    println("indexdocs <INDEX-PATH> ")
    System.exit(0)
  }

  val indexPath = if (args.length > 0) args(0) else "/tmp/wikipedia/index"
  if (indexPath.isEmpty) {
    help()
  }

  val f = new File(indexPath)
  val d = FSDirectory.open(f)
  val stdAnalyzer = new StandardAnalyzer(Version.LUCENE_30)
  val iwriterOut = new IndexWriter(d, stdAnalyzer, IndexWriter.MaxFieldLength.UNLIMITED)
  val FIELD_TEXT = "text"
  val FIELD_ID = "id"
  val FIELD_TITLE = "id"

  val coll = Utilities.getCollection
  val commitFactor = 1000 // commit at every 1000 documents
  for ((x, idx) <- coll.find().zipWithIndex) {
    val id = x.get("id").toString
    val title = x.get("title").toString
    val text = x.get("plaintext").toString
    println("idx: %d, id : %s , title : %s".format(idx, id, title))
    // println("    text  => " + text)

    if (text.size > 0) {
      val doc = new Document()
      val textField = new Field(FIELD_TEXT, text, Field.Store.NO, Field.Index.ANALYZED, Field.TermVector.YES)
      val idField = new Field(FIELD_ID, id, Field.Store.YES, Field.Index.NOT_ANALYZED, Field.TermVector.NO)
      val titleField = new Field(FIELD_TITLE, title, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES)

      doc.add(textField)
      doc.add(idField)
      iwriterOut.addDocument(doc)
      if (idx % commitFactor == 0) {
        println("Writing to disk... Committing ...")
        iwriterOut.commit()
      }
    }
  }
  println("Writing to disk... Committing ...")
  iwriterOut.commit()

  iwriterOut.close()
}
