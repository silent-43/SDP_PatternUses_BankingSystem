package model;

public class StudentAccount extends SavingsAccount {
   private static final long serialVersionUID = 1L;
   String institutionName;

   public StudentAccount(String name, double balance, String institutionName) {
      super(name, balance, 20000.0);
      this.min_balance = 100.0;
      this.institutionName = institutionName;
   }
}
