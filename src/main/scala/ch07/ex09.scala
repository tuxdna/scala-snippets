package ch07

/*
 * 9. Write a program that imports the java.lang.System class, reads
 * the user name from the user.name system property, reads a password
 * from the Console object, and prints a message to the standard
 * error stream if the password is not "secret". Otherwise, print a
 * greeting to the standard output stream. Do not use any other
 * imports, and do not use any qualified names (with dots).
 * 
 */

import java.lang.System
object ex09 extends App {
  val userName = System.getProperty("user.name")
  var password = Console.readLine("password: ")
  if (password == "secret") println("Hello " + userName)
}

