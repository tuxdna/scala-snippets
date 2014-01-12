package ch11

object ex06 extends App {
  class ASCIIArt(val figure: List[String]) {
    override def toString = {
      figure.mkString("\n");
    }

    def +(that: ASCIIArt) = {
      new ASCIIArt(this.figure.zip(that.figure).map { x =>
        x._1 ++ x._2
      })
    }

  }

  val a1 = List(
    """ /\_/\ """,
    """( ' ' )""",
    """(  -  )""",
    """ | | | """,
    """(__|__)""")

  val a2 = List(
    """   -----  """,
    """ / Hello \""",
    """<  Scala |""",
    """ \ Coder /""",
    """   -----  """)

  val art1 = new ASCIIArt(a1)
  val art2 = new ASCIIArt(a2)
  println(art1)
  println
  println(art2)
  println
  println(art1 + art2)
  println
}