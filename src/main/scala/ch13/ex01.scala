package ch13

object ex01 extends App {
  def mapchar(s: String) = {
    s.zipWithIndex.foldLeft(Map[Char, Set[Int]]()) { (m, y) =>
      val (c, i) = y
      m ++ Map(c -> (m.getOrElse(c, Set()) ++ Set(i)))
    }
  }

  println(mapchar("Missippi"))
  // >> Map(M -> Set(0), i -> Set(1, 4, 7), s -> Set(2, 3), p -> Set(5, 6))

}
