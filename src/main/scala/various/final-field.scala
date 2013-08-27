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

// Illegal inheritance:
// Uncomment the line below

// class Changeable1 extends Fixed

