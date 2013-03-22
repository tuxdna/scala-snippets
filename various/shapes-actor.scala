package shapes {
  import scala.actors._
  import scala.actors.Actor

  object ShapeDrawingActor extends Actor {
    def act() {
      loop {
	receive {
	  case s: Shape => s.draw()
	  case "exit" => println("Exiting..."); exit
	  case x: Any => println("Error: Unknown message!"+x)
	}
      }
    }
  }
}
