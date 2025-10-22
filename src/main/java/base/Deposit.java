package base;

public class Deposit implements Account {
    private double balance = 0;

    @Override
    public void addMoney(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to Deposit. Balance = " + balance);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Early withdrawal is not allowed for Deposit. Close account to withdraw.");
    }

    @Override
    public void transferMoney(Account toAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (balance < 1000) {
            System.out.println("You can transfer money only if your balance is at least 1000.");
            return;
        }
        if (balance < amount) {
            System.out.println("Error: Not enough balance to transfer.");
            return;
        }

        balance -= amount;

        System.out.println("Transferred " + amount + " from Deposit");
        toAccount.addMoney(amount);

    }

    @Override
    public void closeAccount() {
        System.out.println("Deposit closed. Final balance: " + balance);
        balance = 0;
    }

    @Override
    public double viewCurrentBalance() {
        System.out.println("Current balance: " + balance);
        return balance;
    }

    @Override
    public void invest(double amount) {
        System.out.println("Investment amount is not allowed for Deposit. Close account.");
    }
}
