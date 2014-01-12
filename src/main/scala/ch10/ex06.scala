package ch10

trait Component
trait AComponent extends Component
trait AButton extends AComponent

trait Container extends Component
trait AContainer extends Container
trait APanel extends AContainer

object ex06 extends App {
  println(classOf[APanel])
}
