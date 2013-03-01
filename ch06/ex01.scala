//1. Write an object Conversions with methods inchesToCentimeters, 
// gallonsToLiters, and milesToKilometers.


object ex01 extends App {

  object Conversions {
     def inchesToCentimeters(inches: Double) = inches * 2.54
     def gallonsToLiters(gallons: Double) = gallons * 3.78541
     def milesToKilometers(miles: Double) = miles * 1.60934
  }

  println(Conversions.inchesToCentimeters(91))
  println(Conversions.gallonsToLiters(22))
  println(Conversions.milesToKilometers(68))
}
