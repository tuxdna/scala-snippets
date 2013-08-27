/*
 * This tool is meant to copy documents from one lucene index to an another index.
 * NOTE: It uses StandardAnalyzer ( version 3.0 ) for tokenizing the fields while copying.
 * 
 * Compile:
 *   scalac -cp lucene-core-3.0.1.jar copyLuceneDocs.scala
 * 
 * Example Usage:
 *   scala -cp lucene-core-3.0.1.jar copyLuceneDocs input-index /tmp/out-index 10
 */

import java.io.File
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.index.IndexReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.util.Version

object copyLuceneDocs {

  def help() {
      println("copyLuceneDocs <IN-INDEX-PATH> <OUT-INDEX-PATH> [LIMIT]")
      System.exit(0)
  }

  def main(args: Array[String]) = {
    val indexPath = if(args.length > 0) args(0) else ""
    if(indexPath.isEmpty) {
      help()
    }

    val outIndexPath = if(args.length > 1) args(1) else ""
    if(outIndexPath.isEmpty) {
      help()
    }

    // default is no limit
    val limit = if(args.length > 2) args(2).toInt else -1
    val f = new File(indexPath)
    val d = FSDirectory.open(f)
    val ir = IndexReader.open(d)
    val totalDocs = ir.maxDoc

    // create output index
    val fOut = new File(outIndexPath)
    val dOut = FSDirectory.open(fOut)
    val createOne = ! IndexReader.indexExists(dOut)
    val stdAnalyzer = new StandardAnalyzer(Version.LUCENE_30)
    val iwriterOut = new IndexWriter(dOut, stdAnalyzer, createOne,
                         IndexWriter.MaxFieldLength.UNLIMITED);


    // take only non-deleted documents
    val docIds = (0 until totalDocs).filter(x => !ir.isDeleted(x) )

    // set upper limit on number of documents to be copied
    val upperLimit = if(limit == -1) docIds.size else limit

    for( i <- 0 to docIds.size if i < upperLimit ) {
      val docId = docIds(i)
      println(i + " -> " + docId)
      val doc = ir.document(docId)
      val fields = doc.getFields
      val docNew = new Document
      for(k <- 0 until fields.size) {
        val fld = fields.get(k)
        println("    * Field: " + fld.name)
        val stored = if(fld.isStored) Field.Store.YES else Field.Store.NO
        val fldNew = if(fld.isBinary) {
               new Field(fld.name, fld.getBinaryValue, stored)
          } else {
               val indexed = Field.Index.toIndex(fld.isIndexed,
                        fld.isTokenized, fld.getOmitNorms)
               val termvector = if(indexed == Field.Index.NO) Field.TermVector.NO else Field.TermVector.toTermVector(
                   fld.isStored, fld.isStoreOffsetWithTermVector,
                   fld.isStorePositionWithTermVector)

               new Field(fld.name, fld.stringValue, stored,
                 indexed, termvector)
           }
        docNew.add(fldNew)
      }

      // verify
      val fieldsNew = docNew.getFields
      for(k <- 0 until fieldsNew.size) {
        val fld = fieldsNew.get(k)
        println("     +> Field: " + fld.name)
      }

      iwriterOut.addDocument(docNew)
      iwriterOut.commit()
    }
    iwriterOut.close()

  }

}
