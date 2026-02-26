package model;

import strategy.SavingsWithdrawStrategy;

public class SavingsAccount extends BankAccount {
   private static final long serialVersionUID = 1L;
   float rate = 0.05F;
   double maxWithLimit;

   public SavingsAccount(String name, double balance, double maxWithLimit) {
      super(name, balance, 2000.0);
      this.maxWithLimit = maxWithLimit;
      // use a strategy to enforce savings-specific withdraw limit
      this.setWithdrawalStrategy(new SavingsWithdrawStrategy(maxWithLimit));
   }

   public double getMaxWithLimit() {
      return this.maxWithLimit;
   }

   public double getNetBalance() {
      double NetBalance = this.getbalance() + this.getbalance() * (double)this.rate;
      return NetBalance;
   }

   public void withdraw(double amount) throws Exception {
      // strategy will validate; delegate to super which applies strategy and balance checks
      super.withdraw(amount);
   }

   @Override
   public String toString() {
      return "[SAVINGS] Name: " + this.name + ", Id: " + this.acc_num + ", Balance: " + this.getbalance() + 
             ", Max Withdrawal: " + this.maxWithLimit + ", Net Balance (with 5% interest): " + String.format("%.2f", this.getNetBalance());
   }
}
