package functions
import java.security.MessageDigest

object HexnBytes {

  def hexToByteArray(str: String) = {
    val f = Character.digit(_: Char, 16)
    (if (str.startsWith("0x")) str.substring(2) else str)
      .grouped(2).map(e => ((f(e(0)) << 4) + f(e(1))).toByte).toArray
  }

  def getPasswordDigest(password: String, salt: String) = {
    val md = MessageDigest.getInstance("MD5")
    val providedPassword = md.digest((password + salt).getBytes)
    providedPassword.map("%02X".format(_)).mkString
  }

  def main(args: Array[String]): Unit = {

    val dbpw = "0xF7B4A17EEAAE2CC9A958CEC635CEEAB4"
    val dbsalt = "9EZ88qhWW0mDW36bsptE3ZT+"
    val pw = "12345678"

    val md = MessageDigest.getInstance("MD5")
    val providedPassword = md.digest((pw + dbsalt).getBytes)
    val x = "0x" + providedPassword.map("%02X".format(_)).mkString
    // res3: String = 0xF7B4A17EEAAE2CC9A958CEC635CEEAB4

    x.grouped(2).toList.tail.map(e => ((Character.digit(e(0), 16) << 4) + Character.digit(e(1), 16)).toByte)

    println(hexToByteArray(x).toList)

  }

}