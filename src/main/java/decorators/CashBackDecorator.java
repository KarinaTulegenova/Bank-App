package decorators;
import base.Account;

public class CashBackDecorator extends AccountDecorator {
    private static final double CASHBACK_RATE = 0.14;
    public CashBackDecorator(Account account) {
        super(account);
    }

    @Override
    public void addMoney(double amount) {
        super.addMoney(amount);
        double cashback = amount * CASHBACK_RATE;
        cashback = Math.round(cashback * 10.0) / 10.0;
        System.out.println("Cashback added: " + cashback);
        super.addMoney(cashback);
    }
}
