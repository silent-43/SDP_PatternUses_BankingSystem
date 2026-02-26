package factory;

import model.BankAccount;
import model.SavingsAccount;
import model.CurrentAccount;
import model.StudentAccount;

public class AccountFactory {
    public static BankAccount createSavings(String name, double balance, double maxWithLimit) {
        return new SavingsAccount(name, balance, maxWithLimit);
    }

    public static BankAccount createCurrent(String name, double balance, String tradeLicense) {
        return new CurrentAccount(name, balance, tradeLicense);
    }

    public static BankAccount createStudent(String name, double balance, String institutionName, double min_balance) {
        return new StudentAccount(name, balance, institutionName);
    }
}
