class Role
case object Manager extends Role
case object Developer extends Role

case class Person(name:String, age: Int, role: Role)

val alice = new Person("Alice", 25, Developer)
val bob = new Person("Bob", 32, Manager)
val charlie = new Person("Charlie", 32, Developer)

for( person <- List(alice, bob, charlie) ) {
  person match {
    case p @ Person(_, _, Manager) => println("%s is overpaid".format(p))
    case p @ Person(_, _, _) => println("%s is underpaid".format(p))
  }
}
