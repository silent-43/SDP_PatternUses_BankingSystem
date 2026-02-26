package strategy;

public interface WithdrawStrategy {
    boolean allow(double currentBalance, double amount);
}
