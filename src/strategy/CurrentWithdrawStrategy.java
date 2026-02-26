package strategy;

public class CurrentWithdrawStrategy implements WithdrawStrategy {
    @Override
    public boolean allow(double currentBalance, double amount) {
        // current accounts use default behavior; allow by strategy
        return true;
    }
}
