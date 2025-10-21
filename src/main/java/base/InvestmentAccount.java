package base;

public class InvestmentAccount implements Account {
    private double balance = 0;

    @Override
    public void addMoney(double amount) {
        if (amount <= 0) { System.out.println("Amount must be > 0"); return; }
        balance += amount;
        System.out.println("Deposited " + amount + " to InvestmentAccount. Balance = " + balance);
    }

    @Override
    public void invest(double amount) {
        if (amount <= 0) { System.out.println("Invest amount must be > 0"); return; }
        if (amount > balance) {
            System.out.println("Not enough balance to invest.");
            return;
        }
        double invested_result = balance - amount;
        double profit = amount * 0.10;
        double profited_result = invested_result + profit;
        balance = profited_result;
        System.out.println("Invested " + amount + ", earned " + profit + ". New balance = " + profited_result);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) { System.out.println("Amount must be > 0"); return; }
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Investment account. Balance = " + balance);
        } else {
            System.out.println("Insufficient funds. Balance = " + balance);
        }
    }

    @Override
    public void transferMoney(Account toAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Error: Transfer amount must be positive.");
            return;
        }
        if (balance < amount) {
            System.out.println("Error: Not enough balance to transfer.");
            return;
        }

        balance -= amount;
        toAccount.addMoney(amount);

        System.out.println("Transferred " + amount + " KZT from Investment to "
                + toAccount.getClass().getSimpleName() + ".");
        System.out.println("Remaining balance: " + balance);
    }

    @Override
    public void closeAccount() {
        System.out.println("InvestmentAccount closed. Final balance: " + balance);
        balance = 0;
    }

    @Override
    public double viewCurrentBalance() {
        return balance;
    }
}
