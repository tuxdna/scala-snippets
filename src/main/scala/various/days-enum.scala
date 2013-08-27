package various.daysenum

object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

import WeekDay._

object test {

  def isWorkingDay(d: WeekDay) = !(d == Sat || d == Sun)

  def isWeekend(d: WeekDay) = d == Sat || d == Sun

  println("Week Days: ")
  WeekDay.values filter isWorkingDay foreach println

  println("Not weekends: ")
  WeekDay.values filter (!isWeekend(_)) foreach println

}