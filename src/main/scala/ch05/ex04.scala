package ch05

//4. Reimplement the Time class from the preceding exercise so that the 
// internal representation is the number of minutes since midnight 
// (between 0 and 24 × 60 – 1). Do not change the public interface. That
//  is, client code should be unaffected by your change.

object ex04 extends App {
  class Time(hrs: Int, min: Int) {
    val time_since_midnight = hrs*60 + min%60
    def hours = time_since_midnight / 60
    def minutes = time_since_midnight % 60
    def before(other: Time) = { 
      if (this.hours < other.hours) true

      else if(this.hours <= other.hours && 
	      this.minutes < other.minutes) true
      else false
    }
  }

  object Time {
     def apply(hrs: Int, min: Int) = {
       new Time(hrs, min)
     }
  }

  val t1 = new Time(13, 15)
  val t2 = new Time(14, 12)
  val t3 = Time(14, 116)
  println(t1.before(t2))
  println(t2.before(t1))
  println(t2.before(t3))
  println(t3.before(t2))
}
