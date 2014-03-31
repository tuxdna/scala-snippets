package ch21

object ex04 {

  object attempt1 {
    trait InputType
    object aString extends InputType
    object anInt extends InputType
    object aDouble extends InputType

    class Fluent {
      var inputType: InputType = aString
      def in(t: InputType): this.type = {
        inputType = t
        this
      }
      def and = in(_)

      def askingFor(msg: String): this.type = {
        println(msg)
        // convert
        inputType match {
          case `aString` => println("Convert to String")
          case `anInt` => println("Convert to Int")
          case `aDouble` => println("Convert to Double")
          case _ => println("Conversion not found")
        }
        this
      }
    }
    def main(args: Array[String]): Unit = {
      def Read = new Fluent
      Read in aString askingFor "Your name" and anInt askingFor "Your age" and aDouble askingFor "Your weight"
    }
  }

  // Final version
  trait InputType { type t }
  object aString extends InputType { type t = String }
  object anInt extends InputType { type t = Int }
  object aDouble extends InputType { type t = Double }

  class FluentDSL(val inputType: InputType) {
    def and(t: InputType): FluentDSL = Read.in(t)

    def askingFor(msg: String): this.type = {
      println(msg)
      val v = inputType match {
        case `aString` => readLine
        case `anInt` => readInt
        case `aDouble` => readDouble
      }
      println(" -> " + v)
      this
    }
  }

  object Read {
    def in(t: InputType): FluentDSL = {
      new FluentDSL(t)
    }
  }

  def main(args: Array[String]): Unit = {
    Read in aString askingFor "Your name" and anInt askingFor "Your age" and aDouble askingFor "Your weight"
  }

}