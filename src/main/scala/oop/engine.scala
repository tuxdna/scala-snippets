package oop

object engine {

  object FuelLevel extends Enumeration {
    type FuelLevel = Value
    val Empty, Reserve, Half, Full = Value
  }

  import FuelLevel._

  // trait Stoppable { def stop }
  trait Stoppable {
    var running: Boolean
    var fuelLevel: FuelLevel
    def stop = {
      if (running) {
        running = false
        println("Engine stopped")
      }
    }
  }
  // trait Startable { def start }
  trait Startable {
    var running: Boolean
    var fuelLevel: FuelLevel
    def start = {
      fuelLevel match {
        case FuelLevel.Empty => println("Cannot start without fuel.")
        case _ if !running => {
          running = true; println("Engine started")
        }
        case _ => println("Engine already running")
      }
    }
  }

  class Engine(hp: Int, state: Boolean, fl: FuelLevel)
    extends Startable with Stoppable {
    val horsePower = hp
    var fuelLevel = fl
    var running = state
    override def toString = "Engine(running: %s, fuelLevel: %s, horsePower: %dBHP)"
      .format(running, fuelLevel, horsePower)
  }

  class TwoStrokeEngine(hp: Int, state: Boolean, fl: FuelLevel)
    extends Engine(hp, state, fl) {
    override def toString = "TwoStroke" + super.toString()
  }

  class FourStrokeEngine(hp: Int, state: Boolean, fl: FuelLevel)
    extends Engine(hp, state, fl) {
    override def toString = "FourStroke" + super.toString()
  }

  trait Make {
    def make: String
  }

  class SteamEngine(hp: Int, state: Boolean, fl: FuelLevel, modelName: String)
    extends Engine(hp, state, fl) with Make {
    private val model = modelName
    def this(hp: Int, state: Boolean, fl: FuelLevel) = this(hp, state, fl, "Toy")
    def make = "SteamEngine - " + model
  }

  def main(args: Array[String]) {

    val e1 = new FourStrokeEngine(40, false, FuelLevel.Empty)
    println(e1)
    e1.start // won't start
    e1.fuelLevel = FuelLevel.Full
    e1.start // will start: fuel-tank is now full
    println(e1 + "\n")

    val e2 = new TwoStrokeEngine(40, false, FuelLevel.Full)
    println(e2)
    e2.start
    println(e2 + "\n")

    val e3 = new SteamEngine(300, true, FuelLevel.Reserve)
    println(e3)
    println(e3.make + "\n")

    val myengines = Array(e1, e2, e3)
    println(myengines + "\n")
  }
}