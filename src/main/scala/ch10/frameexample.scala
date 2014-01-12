package ch10

import javax.swing.JFrame
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.Graphics
import java.awt.Color

object frameexample extends App {
  class DrawRectPanel extends javax.swing.JPanel {
    override def paintComponent(g: Graphics) {
      super.paintComponent(g)
      g.setColor(Color.blue)
      g.drawRect(10, 10, 80, 30)
      g.drawRoundRect(100, 10, 80, 30, 15, 15)

      val thickness = 4
      for (i <- 0 to thickness)
        g.draw3DRect(200 - i, 10 - i, 80 + 2 * i, 30 + 2 * i, true)
      for (i <- 0 to thickness)
        g.draw3DRect(200 - i, 50 - i, 80 + 2 * i, 30 + 2 * i, false)

      g.drawOval(10, 100, 80, 30);
    }
  }

  val frame = new JFrame()
  frame.setTitle("DrawRect")
  frame.setSize(300, 200)

  frame.addWindowListener(new WindowAdapter {
    override def windowClosing(e: WindowEvent) { System.exit(0) }
  })

  val contentPane = frame.getContentPane()
  contentPane.add(new DrawRectPanel())
  frame.show()
}