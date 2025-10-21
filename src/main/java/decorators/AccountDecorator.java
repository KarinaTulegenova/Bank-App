package decorators;
import base.Account;

public abstract class AccountDecorator implements Account {
    protected Account account;

    public AccountDecorator(Account account) {
        this.account = account;
    }

    @Override
    public void addMoney(double amount) {
        account.addMoney(amount);
    }

    @Override
    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    @Override
    public void transferMoney(Account toAccount, double amount){
        account.transferMoney(toAccount, amount);
    }

    @Override
    public void invest(double amount) {
        account.invest(amount);
    }

    @Override
    public void closeAccount() {
        account.closeAccount();
    }

    @Override
    public double viewCurrentBalance() {
        return account.viewCurrentBalance();
    }
}
