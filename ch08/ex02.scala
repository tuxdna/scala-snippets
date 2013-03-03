/*
 * 2. Extend the BankAccount class of the preceding exercise into a
 * class SavingsAccount that earns interest every month (when a
 * method earnMonthlyInterest is called) and has three free deposits
 * or withdrawals every month. Reset the transaction count in the
 * earnMonthlyInterest method.
 */

object ex02 extends App {
  class BankAccount(initialBalance: Double) {
    private var balance = initialBalance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
  }

  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private var txCount = 0
    def earnMonthlyInterest: Unit = {
      super.deposit(10)
      txCount = 0
    }
    override def deposit(amount: Double) = { 
      txCount += 1
      super.deposit(amount- ( if (txCount <= 3) 0 else 1 )  )
    }
    override def withdraw(amount: Double) = {
      txCount += 1
      super.withdraw(amount+ ( if (txCount <= 3) 0 else 1 ) )
    }
  }

  val a1 = new SavingsAccount(100)
  println(a1.deposit(10))
  println(a1.withdraw(10))
  println(a1.withdraw(10))
  println(a1.withdraw(10))
  a1.earnMonthlyInterest
  println(a1.withdraw(10))
}
