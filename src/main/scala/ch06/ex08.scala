package ch06

// 8. Write an enumeration describing the eight corners of the RGB color
// cube. As IDs, use the color values (for example, 0xff0000 for Red).

object ex08 extends App {
   object RGBCorners extends Enumeration {
     val CORNER_000 = Value(0x000000)
     val CORNER_001 = Value(0x0000ff)
     val CORNER_010 = Value(0x00ff00)
     val CORNER_011 = Value(0x00ffff)
     val CORNER_100 = Value(0xff0000)
     val CORNER_101 = Value(0xff00ff)
     val CORNER_110 = Value(0xffff00)
     val CORNER_111 = Value(0xffffff)
   }

   for(c <- RGBCorners.values) println("%6s, %s".format(Integer.toHexString(c.id), c))
}
