package ch10

import java.beans.PropertyChangeSupport
import java.awt.Point
import java.beans.PropertyChangeListener

trait PCS {
  val pcs = new PropertyChangeSupport

  def addPropertyChangeListener(listener: PropertyChangeListener) {
    pcs.addPropertyChangeListener(listener);
  }
  def removePropertyChangeListener(listener: PropertyChangeListener) {
    pcs.removePropertyChangeListener(listener);
  }
}

object ex05 extends App {
  val p = new Point(10, 10) with PCS {
    override def setLocation(x: Int, y: Int) {
      println("location changed")
      pcs.firePropertyChange("location", (this.x, this.y), (x, y))
      this.x = x
      this.y = y
    }
  }
  println(p)
  p.setLocation(1, 2)
  println(p)

}
