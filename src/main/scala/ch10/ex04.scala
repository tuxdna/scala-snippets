package ch10

object ex04 extends App {
  class CryptoLogger(key: Int = 3) {
    val chars = ('a' to 'z') union ('A' to 'Z')
    def encrypt(plaintext: String) = plaintext.map { c =>
      c match {
        case x if chars contains x => {
          val u = x.toUpper
          ('A' + ((u - 'A' + key) % 26)).toChar
        }
        case x => x
      }
    }

    def decrypt(encrypted: String) = encrypted.map { c =>
      c match {
        case x if chars contains x => {
          val u = x.toUpper
          ('A' + ((u - 'A' - key + 26) % 26)).toChar
        }
        case x => x
      }
    }
  }

  val pt = "the quick brown fox jumps over the lazy dog"

  val crpyto = new CryptoLogger

  val enc = crpyto.encrypt(pt)
  println(enc)
  println(crpyto.decrypt(enc))
}