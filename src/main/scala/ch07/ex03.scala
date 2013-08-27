package ch07

/* 3. Write a package random with functions
 * 
 *   nextInt(): Int,
 *   nextDouble(): Double
 *   setSeed(seed: Int): Unit
 * 
 * To generate random numbers, use the linear congruential generator
 * next = previous × a + b mod 2n,
 * where a = 1664525, b = 1013904223, and n = 32.
 * 
 */

package object random {
  var _seed = 0
  val a = 1664525; val b = 1013904223; val n = 32
  def update = {val previous = _seed; _seed = previous * a + b % ( 2*n )}
  def nextInt(): Int = { update; _seed }
  def nextDouble(): Double = {update; _seed}
  def setSeed(seed: Int): Unit = { _seed = seed }
}

package random {}

object ex03 extends App {
  random.setSeed(12314)
  for(i <- 1 to 10) println(random.nextInt())
  for(i <- 1 to 10) println(random.nextDouble())
}
