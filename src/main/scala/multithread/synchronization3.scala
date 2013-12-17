package multithread

object synchronization3 extends App {

  class BankAccount {
    private var balance = 0
    def withdraw(amount: Int): Int = this.synchronized {

      if (amount > 0 && balance >= amount) { balance = balance - amount; balance }
      else throw new Error("Insufficient funds!")
    }

    def deposit(amount: Int) = this.synchronized {
      if (amount > 0) balance = balance + amount; balance
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

  // this should be 190
  println(a1.current_balance)

  def transfer(from: BankAccount, to: BankAccount, amount: Int): Unit = {
    from.synchronized {
      to.synchronized {
        from.withdraw(amount)
        to.deposit(amount)
      }
    }
  }

  println("a1: " + a1.current_balance)

  val a2 = new BankAccount
  a2.deposit(100)

  println("a2: " + a2.current_balance)

  println("transfer 50 from a1 to a2: ")
  transfer(a1, a2, 50)

  println("a1: " + a1.current_balance)
  println("a2: " + a2.current_balance)

  new Thread(new Runnable {
    def run = {

      println("transfer 50 from a1 to a2: ")
      transfer(a1, a2, 50)
    }
  }).start()

  new Thread(new Runnable {
    def run = {
      println("transfer 60 from a2 to a1: ")
      transfer(a2, a1, 60)

    }
  }).start()

  println("a1: " + a1.current_balance)
  println("a2: " + a2.current_balance)

}
