package decorators;

import base.Account;

public class KidsCardDecorator extends AccountDecorator {
    private static final double welcome_bonus = 1000.0;

    public KidsCardDecorator(Account account) {
        super(account);
    }

    @Override
    public void addMoney(double amount) {
        super.addMoney(amount);
        super.addMoney(welcome_bonus);
        System.out.println("Cashback added: " + welcome_bonus);
    }

}
