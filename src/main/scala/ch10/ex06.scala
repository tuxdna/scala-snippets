package ch10

trait Component
trait JComponent extends Component
trait JButton extends JComponent

trait Container extends Component
trait JContainer extends Container
trait JPanel extends JContainer

object ex06 extends App {
  println(classOf[JPanel])
}
