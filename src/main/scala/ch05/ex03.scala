package ch05

//3. Write a class Time with read-only properties hours and minutes and a
//   method before(other: Time): Boolean that checks whether this time
//   comes before the other. A Time object should be constructed as
//   new Time(hrs, min), where hrs is in military time format (between
//   0 and 23).

object ex03 extends App {
  class Time(hrs: Int, min: Int) {
    if ( ( 0 to 23 contains hrs) && ( 0 to 59 contains min ) ) {
      println("Passed")
    } else { println("Values out of range") }

    def hours = hrs
    def minutes = min
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
