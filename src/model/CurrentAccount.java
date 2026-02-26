package model;

import strategy.CurrentWithdrawStrategy;

public class CurrentAccount extends BankAccount {
   private static final long serialVersionUID = 1L;
   String tradeLicenseNumber;

   public CurrentAccount(String name, double balance, String tradeLicenseNumber) {
      super(name, balance, 5000.0);
      this.tradeLicenseNumber = tradeLicenseNumber;
      this.setWithdrawalStrategy(new CurrentWithdrawStrategy());
   }

   @Override
   public String toString() {
      return "[CURRENT] Name: " + this.name + ", Id: " + this.acc_num + ", Balance: " + this.getbalance() + 
             ", Trade License: " + this.tradeLicenseNumber;
   }
}
