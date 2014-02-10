package ml

import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.index.IndexReader
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.analysis.SimpleAnalyzer
import java.io.File
import scala.io.Source
import scala.collection.JavaConversions._
import scala.collection.mutable
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.PrintStream

object termsMatrix extends App {

  val FIELD_BODY = "body"
  val FIELD_LOCATION = "location"
  case class D(location: String, body: String)

  def printTermsMatrix(outputLocation: File, ir: IndexReader) = {
    println("Number of Documents: " + ir.numDocs())
    val terms = ir.terms();
    val termIdMap = mutable.Map.empty[String, Int]
    var currentTermId = 1
    while (terms.next()) {
      val term = terms.term();
      val termString = term.text()
      val termId = termIdMap.get(termString) match {
        case Some(id) => id
        case None => {
          val id = currentTermId
          currentTermId += 1
          id
        }
      }
      termIdMap += (termString -> termId)
    }

    val entriesFile = new File(outputLocation, "termsList.dat")
    val psEntries = new PrintStream(entriesFile)
    
    println("Writing (termId -> term) entries to: " + entriesFile.getAbsolutePath())
    termIdMap.foreach { e =>
      psEntries.println(e._2 + "\t" + e._1)
    }
    psEntries.close()

    val matrixFile = new File(outputLocation, "matrix.dat")
    val psMatrix = new PrintStream(matrixFile)
    
    println("Writing matrix entries to: " + matrixFile.getAbsolutePath())
    for (docId <- 0 until ir.numDocs() if !ir.isDeleted(docId)) {
      val d = ir.document(docId)
      val fieldBody = d.getField(FIELD_BODY)
      val tfVector = ir.getTermFreqVector(docId, FIELD_BODY)
      tfVector.getTerms.zip(tfVector.getTermFrequencies()).foreach { e =>
        val term = e._1
        val freq = e._2
        val termId = termIdMap(term)
        psMatrix.println(List(docId + 1, termId, freq).mkString(" "))
      }
    }
    psMatrix.close()
  }

  val indexPath = args(0)
  val outputPath = args(1)
  val indexLocation = new File(indexPath)
  println("Reading")
  if (indexLocation.isDirectory()) {
    println("Reading")
    val dir = FSDirectory.open(indexLocation)
    val reader = IndexReader.open(dir, true)
    val outputLocation = new File(outputPath)
    if (!outputLocation.isDirectory()) {
      outputLocation.mkdirs()
    }
    printTermsMatrix(outputLocation, reader)
  }
}