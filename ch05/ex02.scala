// 2. Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.

object ex02 extends App {
  class BankAccount {
    var bal = 0
    def balance = bal
    def deposit(amt: Int) = bal += amt
    def withdraw(amt: Int) = bal -= amt
  }

  val a1 = new BankAccount
  println(a1.balance)
  a1.deposit(10)
  println(a1.balance)
  a1.withdraw(10)
  println(a1.balance)
}
