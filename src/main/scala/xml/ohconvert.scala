package xml

import java.io.File
import java.io.FileWriter

object ohconvert {
  val UID = "UID"
  val mesh = "MeshTerms"
  val Title = "Title"
  val Summary = "Summary"
  def ohXmlToText(file: File) = {
    val xmlstr = scala.io.Source.fromFile(file).mkString
    val fx = scala.xml.XML.loadString(xmlstr)
    val uid = (fx \ UID)(0).text
    val meshTerms = (fx \ mesh)(0).text
    val title = (fx \ Title)(0).text
    val summary = (fx \ Summary)(0).text
    List(uid, meshTerms, title, summary).mkString("\n\n")
  }
  def main(args: Array[String]) {
    val inputDir = if (args.length > 0) args(0)
    else "oh_data/"

    val outputDir = if (args.length > 1) args(1)
    else "/tmp/output"

    val d = new File(inputDir)

    val files = d.listFiles.filter(x => x.getPath.endsWith(".xml"))
    for (file <- files) {
      println("file: " + file)
      val allText = ohXmlToText(file)
      val outputFile = new File(outputDir, file.getName.replaceFirst(".xml", ".txt"))
      println("writing to: " + outputFile)
      val writer = new FileWriter(outputFile)
      writer.write(allText)
      writer.close()
    }
  }
}
