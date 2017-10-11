// package so

// object Main {
//   def main(args: Array[String]) {
//     println("Helo")
//   }
// }

// object CaseInheritSample {

//   trait Breed

//   case object Pincher extends Breed

//   case object Haski extends Breed

//   trait Foox[T] {
//     self: T =>

//     def age: Int

//     def addToAge(i: Int): T = {
//       self match {
//         case x: Dog =>
//           x.copy(age = age + 1).asInstanceOf[T]
//         case x: Person =>
//           x.copy(age = age + 1).asInstanceOf[T]
//       }
//     }
//   }

//   //  case class Dog(breed: Breed, age: Int) extends Foox
//   //
//   //  case class Person(name: String, age: Int) extends Foox

//   case class Dog(breed: Breed, age: Int) extends Foox[Dog] {
//     //    def addToAge(i: Int) = copy(age = age + i)
//   }

//   case class Person(name: String, age: Int) extends Foox[Person] {
//     //    def addToAge(i: Int) = copy(age = age + i)
//   }

//   def main(args: Array[String]) {
//     val d = Dog(Pincher, 10)
//     println(d.addToAge(1))
//   }

// }
