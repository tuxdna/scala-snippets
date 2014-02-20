object quicklook {

  // List/Array
  val lst = List(1, 2, 3, 4, 5, 6, 7, 8, 10)
  val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  // Set / Map
  val T = Set("casey", "drew", "jade")
  val V = Set("drew", "jade", "glen")
  val S = Set("alex", "hunter", "casey", "drew")
  val U = V union T union S union Set("blair", "erin", "francis", "era")
  val commonSet = S & T & V
  val radiation = Map('a' -> "alpha",
    'o' -> "omega", 'g' -> "gamma")
  radiation('g')

  // Tuple
  val tuple1 = Tuple1(1)
  val tuple2 = ('a', 1)
  val tuple3 = ('a', 1, "name")
  val anotherTuple = (
    tuple1._1,
    tuple2._2,
    tuple3._1,
    tuple3._3)

  // if-else
  import java.util.Calendar
  val now = Calendar.getInstance()
  val hour = now.get(Calendar.HOUR_OF_DAY)
  println(if (hour < 12) "good morning" else "good afternoon")

  // loops
  val numbers = (1 to 10) toList

  var index = 0
  while (index < numbers.length) {
    print(numbers(index) + " ")
    index += 1
  }

  index = 0
  do {
    print(numbers(index) + " ")
    index += 1
  } while (index < numbers.length)

  // case-match i.e. pattern matching
  val dow = now.get(Calendar.DAY_OF_WEEK)
  dow match {
    case 1 => "Sunday"
    case 2 => "Monday"
    case 3 => "Tuesday"
    case 4 => "Wednesday"
    case 5 => "Thursday"
    case 6 => "Friday"
    case 7 => "Saturday"
  }

  dow match {
    case x if x == 1 || x == 7 => "Weekend"
    case _ => "Workday"
  }

  val day2day = Map(1 -> "Sunday", 2 -> "Monday", 3 -> "Tuesday",
    4 -> "Wednesday", 5 -> "Thursday", 6 -> "Friday",
    7 -> "Saturday")

  day2day(dow)

  // more pattern-matching
  val text =
    """|Hello Scala World
    |Scala is a wonderful language
    |OO + FP make Scala wonderful
    |You should also try Scala""".stripMargin('|')

  val nextToScala = """.*\bScala( (\w+)).*""".r

  text.split("\n") map {
    case nextToScala(x, w) => w
    case _ => ""
  }

  var divisor = 0

  def divide(n: Int) = try {
    if (scala.util.Random.nextInt(10) < 5) throw new RuntimeException
    n / divisor
  } catch {
    case e: ArithmeticException => {
      println("Check your calculations!")
      Int.MaxValue
    } case _: RuntimeException => {
      println("A internal error occured")
      0
    }
  } finally {
    // return zero by default
  }

  divide(10)
  divisor = 2
  divide(10)
  divide(5)

  // composing functions
  def f(x: Double) = x * x
  def g(y: Double) = 1 / y

  def gof(x: Double) = g(f(x))
  gof(2)
  gof(4)

  // first class functions
  def add(a: Int, b: Int) = a + b
  val myfunc = (a: Int, b: Int) => a + b
  val otherfunc = add _

  add(2, 4)
  myfunc(4, 5)
  otherfunc(6, 7)

  (x: Int, y: Int, z: Int) => x * y * z

  ((x: Int, y: Int, z: Int) => x * y * z)(5, 4, 3)

  // higher-order functions
  def transform(fn: (Int) => Int, number: Int) = fn(number)
  val plus5 = transform((x: Int) => x + 5, _: Int)
  val into3 = transform((x: Int) => x * 3, _: Int)
  plus5(6)
  into3(6)

  // pure-function

  def stat(num: List[Int]) = {
    (num.sum, num.sum / num.length.toDouble)
  }

  val list1 = List(1, 2, 3, 4, 5)
  val list2 = List(6, 7, 8, 9, 10)

  (stat(list1), stat(list2))

  // recursion
  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  factorial(5)

  // lazy val
  lazy val a = b + 1
  lazy val b = 1
  a
  b

  // call by name
  def withinTx(block: () => Unit) = {
    println("Begin TX"); block();
    println("End TX")
  }

  withinTx { () => println("Performing operation") }

  def insideTx(block: => Unit) = { println("Begin TX"); block; println("End TX") }
  insideTx { println("Performing operation") }

  // map-shuffle-reduce
  val result = (1 to 20)
    .map(x => x * x)
    .groupBy(_ % 5).par
    .map { y => y._2.sum }
    .sum

  // map, filter, fold, reduce

  lst.map(x => x * x)
  lst.filter(x => x % 3 == 0)
  lst.reduce((x, y) => x + y)
  lst.fold(0)((x, y) => x + y)
  lst.reduce((x, y) => x * y)
  lst.fold(1)((x, y) => x * y)
  lst.reduceLeft((x, y) => x * y)
  lst.foldLeft(1)((x, y) => x * y)
  lst.reduceRight((x, y) => x * y)
  lst.foldRight(1)((x, y) => x * y)

  // companion objects
  class Animal(name: String, lifespan: Int) {
    def makeNoise(volume: Int): String = { name + " at volume: " + volume }
  }

  object Animal {
    def apply(name: String, lifespan: Int) = new Animal(name, lifespan)
  }

  val a0 = new Animal("Mammoth", 100)
  val a1 = Animal("Elephant", 40)

  def veryLoud(animal: { def makeNoise(vol: Int): String }) = {
    animal.makeNoise(10)
  }

  veryLoud(a0)
}
