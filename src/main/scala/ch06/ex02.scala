package ch06

// 2. The preceding problem wasn’t very object-oriented. Provide a general
// super- class UnitConversion and define objects InchesToCentimeters, 
// GallonsToLiters, and MilesToKilometers that extend it.

object ex02 extends App {

  abstract class UnitConversion(conversionFactor: Double) {
    def convert(v: Double) = v * conversionFactor
  }

  object InchesToCentimeters extends UnitConversion(2.54)
  object GallonsToLiters extends UnitConversion(3.78541)
  object MilesToKilometers extends UnitConversion(1.60934)

  println(InchesToCentimeters.convert(91))
  println(GallonsToLiters.convert(22))
  println(MilesToKilometers.convert(68))
}
