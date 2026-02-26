package strategy;

public class SavingsWithdrawStrategy implements WithdrawStrategy {
    private double maxLimit;

    public SavingsWithdrawStrategy(double maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public boolean allow(double currentBalance, double amount) {
        return amount <= maxLimit;
    }
}
