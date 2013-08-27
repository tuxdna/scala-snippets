package ch05

// 8. Make a class Car with read-only properties for manufacturer, model
// name, and model year, and a read-write property for the license plate.
// Supply four constructors. All require the manufacturer and model name.
// Optionally, model year and license plate can also be specified in the
// constructor. If not, the model year is set to -1 and the license plate
// to the empty string. Which constructor are you choosing as the primary
// constructor? Why?

object ex08 extends App {
  class Car(val mf: String,
	    val mn: String) {
    val manufacturer = mf
    val modelName = mn
    private var _modelYear = -1
    private var _licensePlate = ""
    def modelYear = _modelYear
    def licensePlate = _licensePlate
    def licensePlate_=(l:String) =  _licensePlate = l

    def this(mf: String, mn: String, my: Int = -1) = {
       this(mf, mn); _modelYear = my; _licensePlate = ""
    }
    def this(mf: String, mn: String, lp: String) = {
       this(mf, mn); _modelYear = -1; _licensePlate = licensePlate
    }
    def this(mf: String, mn: String, my: Int, lp: String) = {
       this(mf, mn); _modelYear = my; _licensePlate = lp
    }
  }

  def print(c: Car) {
    println("Manufacturer: " + c.manufacturer)
    println("Model: " + c.modelName)
    println("Year: " + c.modelYear)
    println("License Plate: " + c.licensePlate)
    println()
  }

  val c1 = new Car("Renault", "Duster", 2012, "DL-ABCX-1234")
  val c2 = new Car("Renault", "Duster", "DL-ABCX-1234")
  val c3 = new Car("Renault", "Duster", 2012)
  val c4 = new Car("Renault", "Duster")
  for(c <- Array(c1,c2,c3,c4)) print(c)
}
