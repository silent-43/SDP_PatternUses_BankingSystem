package model;

import java.io.Serializable;
import strategy.WithdrawStrategy;

public class BankAccount implements Serializable {
   private static final long serialVersionUID = 1L;
   public String name;
   private double balance;
   public double min_balance;
   public String acc_num;
   // strategy for withdraw rules (transient to avoid serializing strategies)
   protected transient WithdrawStrategy withdrawStrategy;

   public BankAccount(String name, double balance, double min_balance) {
      this.name = name;
      this.balance = balance;
      this.min_balance = min_balance;
      this.acc_num = String.valueOf(10000 + (int)(Math.random() * 89999.0));
   }

   public void deposit(double amount) {
      this.balance += amount;
   }

   public void withdraw(double amount) throws Exception {
      if (this.withdrawStrategy != null && !this.withdrawStrategy.allow(this.balance, amount)) {
         throw new Exception("Withdraw rule prevented operation");
      }
      if (this.balance - amount >= this.min_balance && amount <= this.balance) {
         this.balance -= amount;
      } else {
         throw new Exception("Insufficient Balance");
      }
   }

   public void setWithdrawalStrategy(WithdrawStrategy s) {
      this.withdrawStrategy = s;
   }

   public double getbalance() {
      return this.balance;
   }

   public String toString() {
      return "Name: " + this.name + ", Id: " + this.acc_num + ", Balance: " + this.balance;
   }
}
