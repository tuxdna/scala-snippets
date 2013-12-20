package ch10

import java.awt.Rectangle
import javax.swing.JFrame
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.Container
import java.awt.Graphics
import java.awt.Color
import javax.swing.JPanel
import java.awt.geom.Ellipse2D

object ex01 extends App {

  val frame = new JFrame()
  frame.setTitle("RectangleLike")
  frame.setSize(300, 200)
  frame.addWindowListener(new WindowAdapter {
    override def windowClosing(e: WindowEvent) { System.exit(0) }
  })

  val contentPane = frame.getContentPane()
  contentPane.add(new JPanel {
    override def paintComponent(g: Graphics) {
      super.paintComponent(g)
    }
  })

  frame.show()

  // TODO: Provide a graphical implementation  
  trait RectangleLike {
    def translate(x: Int, y: Int) = { println(s"translate($x, $y)") }
    def grow(x: Int, y: Int) = { println(s"grow($x, $y)") }
  }

  val egg = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike

  egg.translate(10, -10)
  egg.grow(10, 20)

}