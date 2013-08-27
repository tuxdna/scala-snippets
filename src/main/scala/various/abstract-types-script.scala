import java.io._

abstract class BulkReader {
}

class StringBulkReader(val source: String) extends BulkReader {
}

class FileBulkReader(val source: File) extends BulkReader {

}
