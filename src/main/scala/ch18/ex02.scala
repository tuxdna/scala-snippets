package ch18

object ex02 {
  object around
  object then
  object show

  class Bug {
    var pos = 0
    var dir = +1
    def move(d: Int): this.type = { pos += d * dir; this }
    def and(action: show.type): this.type = { println(pos); this }
    def and(action: then.type): this.type = this
    def turn(d: around.type) = { dir *= -1; this }
  }

  def main(args: Array[String]): Unit = {
    val bugsy = new Bug()
    bugsy move 4 and show and then move 6 and show turn around move 5 and show
  }
}
