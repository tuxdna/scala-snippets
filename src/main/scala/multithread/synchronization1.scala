package multithread

object synchronization1 extends App {

  class BankAccount {
    private var balance = 0
    def withdraw(amount: Int): Int =
      if (amount > 0 && balance >= amount) { balance = balance - amount; balance }
      else throw new Error("Insufficient funds!")

    def deposit(amount: Int) = {
      if (amount > 0) {
        balance = balance + amount
      }
      balance
    }

    def current_balance = balance
  }

  val a1 = new BankAccount
  a1.deposit(100)
  println(a1.current_balance)
  a1.withdraw(10)
  println(a1.current_balance)
  a1.withdraw(50)
  println(a1.current_balance)
  a1.deposit(60)
  println(a1.current_balance)

  val runnable1 = new Runnable {
    def run = {
      a1.deposit(40)
    }
  }

  val runnable2 = new Runnable {
    def run = {
      a1.deposit(50)
    }
  }

  println("After running threads:")
  
  val thread1 = new Thread(runnable1)
  val thread2 = new Thread(runnable2)

  thread1.start()
  thread2.start()
  
  thread1.join()
  thread2.join()

  // if its not 190, we have a problem
  println(a1.current_balance)
  
  
}
