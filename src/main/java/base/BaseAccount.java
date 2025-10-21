package base;

public class BaseAccount implements Account {
    private double balance = 0;

    @Override
    public void addMoney(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to KaspiGold. Balance = " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) { System.out.println("Amount must be > 0"); return; }
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from KaspiGold. Balance = " + balance);
        } else {
            System.out.println("Insufficient funds. Balance = " + balance);
        }
    }

    @Override
    public void transferMoney(Account toAccount, double amount) {
        if (toAccount == null) {
            System.out.println("Error: Target account not found.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Error: Transfer amount must be positive.");
            return;
        }
        if (balance < amount) {
            System.out.println("Error: Not enough balance to transfer.");
            return;
        }

        balance -= amount;
        System.out.println("Transferred " + amount + " KZT from KaspiGold.");
        toAccount.addMoney(amount);
    }

    @Override
    public void closeAccount() {
        System.out.println("KaspiGold closed. Final balance: " + balance);
        balance = 0;
    }

    @Override
    public double viewCurrentBalance() {
        System.out.println("Current balance: " + balance);
        return balance;
    }

    @Override
    public void invest(double amount) {
        System.out.println("Investment amount is not allowed for Kaspi Gold. Close account.");
    }
}
