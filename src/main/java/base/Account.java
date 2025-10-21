package base;

public interface Account {
    void addMoney(double amount);
    void withdraw(double amount);
    void transferMoney(Account toAccount, double amount);
    void closeAccount();
    double viewCurrentBalance();
    void invest(double amount);
}
