package model;

import javax.swing.DefaultListModel;
import java.io.Serializable;

import factory.AccountFactory;
import strategy.SavingsWithdrawStrategy;
import strategy.CurrentWithdrawStrategy;
import model.BankAccount;
import model.SavingsAccount;
import model.CurrentAccount;

public class Bank implements Serializable {
   private static final long serialVersionUID = 1L;
   private static Bank instance;
   private BankAccount[] accounts = new BankAccount[100];
   private String filepath = "C:\\Temp\\Bank.txt";

   private Bank() {
   }

   // Singleton accessor
   public static synchronized Bank getInstance() {
      if (instance == null) {
         instance = new Bank();
      }
      return instance;
   }

   public int addAccount(BankAccount acc) {
      int i;
      for(i = 0; i < 100 && this.accounts[i] != null; ++i) {
      }
      this.accounts[i] = acc;
      return i;
   }

   // convenience factory-backed adders
   public int addAccount(String name, double balance, double maxWithLimit) {
      BankAccount acc = AccountFactory.createSavings(name, balance, maxWithLimit);
      return this.addAccount(acc);
   }

   public int addAccount(String name, double balance, String tradeLicense) {
      BankAccount acc = AccountFactory.createCurrent(name, balance, tradeLicense);
      return this.addAccount(acc);
   }

   public int addAccount(String name, String institutionName, double balance, double min_balance) {
      BankAccount acc = AccountFactory.createStudent(name, balance, institutionName, min_balance);
      return this.addAccount(acc);
   }

   public BankAccount findAccount(String aacountNum) {
      for(int i = 0; i < 100 && this.accounts[i] != null; ++i) {
         if (this.accounts[i].acc_num.equals(aacountNum)) {
            return this.accounts[i];
         }
      }
      return null;
   }

   public void deposit(String aacountNum, double amt) throws Exception {
      if (amt < 0.0) {
         throw new Exception("Invalid Deposit amount");
      }
      BankAccount temp = this.findAccount(aacountNum);
      if (temp == null) {
         throw new Exception("Account Not Found");
      }
      temp.deposit(amt);
   }

   public void withdraw(String aacountNum, double amt) throws Exception {
      BankAccount temp = this.findAccount(aacountNum);
      if (temp == null) {
         throw new Exception("Account Not Found");
      } else if (amt <= 0.0) {
         throw new Exception("Invalid Amount");
      } else if (amt > temp.getbalance()) {
         throw new Exception("Insufficient Balance");
      } else {
         temp.withdraw(amt);
      }
   }

   public DefaultListModel<String> display() {
      DefaultListModel<String> list = new DefaultListModel<>();
      for(int i = 0; i < 100 && this.accounts[i] != null; ++i) {
         list.addElement(this.accounts[i].toString());
      }
      return list;
   }

   public String getFilepath() {
      return filepath;
   }

   public void setFilepath(String filepath) {
      this.filepath = filepath;
   }

   // reinitialize transient fields after deserialization
   public void reinitializeTransients() {
      for (int i = 0; i < this.accounts.length && this.accounts[i] != null; i++) {
         BankAccount a = this.accounts[i];
         if (a instanceof SavingsAccount) {
            SavingsAccount s = (SavingsAccount)a;
            s.setWithdrawalStrategy(new SavingsWithdrawStrategy(s.getMaxWithLimit()));
         } else if (a instanceof CurrentAccount) {
            a.setWithdrawalStrategy(new CurrentWithdrawStrategy());
         }
      }
   }

   // utility for TableModel to access raw accounts
   public BankAccount[] getAllAccounts() {
      int count = 0;
      for (int i = 0; i < accounts.length && accounts[i] != null; i++) count++;
      BankAccount[] res = new BankAccount[count];
      for (int i = 0; i < count; i++) res[i] = accounts[i];
      return res;
   }

   // replace singleton instance (used by DAO when loading)
   public static synchronized void replaceInstance(Bank b) {
      instance = b;
   }
}
