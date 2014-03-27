package ch15

import org.junit.Test
import org.junit.Assert._
import org.junit.runner.JUnitCore
import org.junit.Before

class Arithmetic {
  def sum(x: Int, y: Int) = x + y
  def divide(x: Int, y: Int) = x / y
}

class ArithmeticTest {
  private var my: Arithmetic = _

  @Before
  def setUp() {
    my = new Arithmetic
  }

  @Test
  def testSumHappy() {
    assertEquals(my.sum(1, 2), 3)
  }

  @Test(timeout = 100)
  def testSumSad() {
    assertEquals(my.sum(1, 2), 3)
  }

  @Test
  def testDivideHappy() {
    assertEquals(my.divide(4, 2), 2)
  }

  @Test(expected = classOf[ArithmeticException])
  def testDivideSad() {
    assertEquals(my.divide(4, 0), 2)
  }
}

object ex01 {
  def main(args: Array[String]) {
    JUnitCore.runClasses(classOf[ArithmeticTest])
  }
}