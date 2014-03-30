package ch18

object ex01 {
  class Bug {
    var pos = 0
    var dir = +1
    def move(d: Int) = { pos += d * dir; this }
    def show() = { println(pos); this }
    def turn() = { dir *= -1; this }
  }

  def main(args: Array[String]): Unit = {
    val bugsy1 = new Bug()
    bugsy1.move(4)
    bugsy1.show
    bugsy1.move(6)
    bugsy1.show
    bugsy1.turn
    bugsy1.move(5)
    bugsy1.show

    val bugsy = new Bug()
    bugsy.move(4).show.move(6).show.turn.move(5).show

  }
}

