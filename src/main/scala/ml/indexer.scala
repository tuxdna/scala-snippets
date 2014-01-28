package ml

import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.analysis.SimpleAnalyzer
import java.io.File
import scala.io.Source

object indexer extends App {

  val FIELD_BODY = "body"
  val FIELD_LOCATION = "location"
  case class D(location: String, body: String)

  def indexAll(inputLocation: File, outputLocation: File, writer: IndexWriter) = {
    inputLocation.listFiles().filter(_.isFile()).foreach { f =>
      val content = Source.fromFile(f).getLines.mkString
      val d = D(f.getAbsolutePath(), content)
      val doc = new Document
      println(d.location)

      val fieldBody = new Field(FIELD_BODY, d.body,
        Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES)
      val fieldLocation = new Field(FIELD_LOCATION, d.location,
        Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES)

      doc.add(fieldBody)
      doc.add(fieldLocation)
      writer.addDocument(doc)
      writer.commit()
    }
  }

  val inputPath = args(0)
  val outputPath = args(1)
  val corpusLocation = new File(inputPath)
  val indexLocation = new File(outputPath)
  if (corpusLocation.isDirectory()) {
    if(! indexLocation.isDirectory()) {
      indexLocation.mkdirs()
    }
    
    val dir = FSDirectory.open(indexLocation);
    val analyzer = new SimpleAnalyzer()
    val writer = new IndexWriter(dir, analyzer,
      IndexWriter.MaxFieldLength.UNLIMITED);

    indexAll(corpusLocation, indexLocation, writer)
  }
}