package ch10

object ch10 extends App {
  trait Logger {
    def log(msg: String)
  }

  trait Logged {
    def log(msg: String) {}
  }

  trait TimestampLogger extends Logged {
    override def log(msg: String) {
      super.log(new java.util.Date() + " " + msg)
    }
  }

  trait ConsoleLogger extends Logged {
    override def log(msg: String) {
      println(msg)
    }
  }

  trait ShortLogger extends Logged {
    override def log(msg: String) {
      super.log(if (msg.length > 30) msg.substring(27) + "..." else msg)
    }
  }

  abstract class Account(var balance: Double) extends Logged {
    def withdraw(amount: Double) = {
      balance = balance - amount
    }
  }

  abstract class SavingsAccount(balance: Double) extends Account(balance) {
    override def withdraw(amount: Double) {
      log("Withdraw " + amount)
      if (amount > balance) {
        log("You have insufficient balance: " + balance + " And you have tried to withrdraw: " + amount)
      } else {
        super.withdraw(amount)
        log("Transaction complete: You current balance: " + balance)
      }
    }
  }

  val act = new SavingsAccount(100) with ConsoleLogger with TimestampLogger with ShortLogger

  act.withdraw(190)
}
