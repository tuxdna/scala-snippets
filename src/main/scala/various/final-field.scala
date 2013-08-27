package various.finalfield

class NotFixed {
  final def fixedMethod = "fixed"
}

class Changeable2 extends NotFixed {
  override def fixedMethod = "not fixed"
}

final class Fixed {
  def doSomething = "Fixed did something!"
}

class Changeable1 extends Fixed

