package various.maps

object test {

  /** Maps are easy to use in Scala. */
  object Maps {
    val colors = Map("red" -> 0xFF0000,
      "turquoise" -> 0x00FFFF,
      "black" -> 0x000000,
      "orange" -> 0xFF8040,
      "brown" -> 0x804000)
    def main(args: Array[String]) {
      for (name <- args) println(
        colors.get(name) match {
          case Some(code) =>
            name + " has code: " + code
          case None =>
            "Unknown color: " + name
        })
    }
  }

  // Maps.main(Array("black"))

  val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  val scores_mutable = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  val scores_empty = new scala.collection.mutable.HashMap[String, Int]
  val bobScore1 = if (scores.contains("Bob")) scores("Bob") else 0
  val bobScore2 = scores.getOrElse("Bob", 0)

  scores_mutable("Bob") = 10 // mutable
  scores_mutable += ("Bob" -> 10, "Fred" -> 7)
  scores_mutable -= "Alice"

  for ((k, v) <- scores) yield (k, v * v)

  val sorted_map = scala.collection.immutable.SortedMap("Alice" -> 10,
    "Fred" -> 7, "Bob" -> 3, "Cindy" -> 8)

  val months = scala.collection.mutable.LinkedHashMap("January" -> 1,
    "February" -> 2, "March" -> 3, "April" -> 4, "May" -> 5 // ...
    )

  import scala.collection.JavaConversions.mapAsScalaMap
  val scores_tm: scala.collection.mutable.Map[String, Int] =
    new java.util.TreeMap[String, Int]

  import scala.collection.JavaConversions.propertiesAsScalaMap
  val props: scala.collection.Map[String, String] = System.getProperties()

  import scala.collection.JavaConversions.mapAsJavaMap
  import java.awt.font.TextAttribute._ // Import keys for map below
  val attrs = Map(FAMILY -> "Serif", SIZE -> 12) // A Scala map
  val font = new java.awt.Font(attrs) // Expects a Java map

  val t = (1, 3.14, "Fred")
  val second = t._2 // Sets second to 3.14
  val (first, second, third) = t
  val (first, second, _) = t

  "New York Yankees".partition(_.isUpper)
  val symbols = Array("<", "-", ">")
  val counts = Array(2, 10, 2)
  val pairs = symbols.zip(counts)
  for ((s, n) <- pairs) Console.print(s * n) // Prints <<---------->>

}