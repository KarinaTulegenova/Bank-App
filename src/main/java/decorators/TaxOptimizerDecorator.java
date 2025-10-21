package decorators;

import base.Account;
import base.InvestmentAccount;

public class TaxOptimizerDecorator extends AccountDecorator {
    private static final double optimized_value = 0.9;
    public TaxOptimizerDecorator(Account account) {
        super(account);
        System.out.println("Tax optimization module successfully activated for this account.");
    }

    public void invest(double amount) {
        double optimized = amount * optimized_value;
        System.out.println("Tax optimized investment: " + optimized);
        if (account instanceof InvestmentAccount inv) {
            inv.invest(optimized);
        } else {
            System.out.println("This account type does not support investment operations.");
        }
    }

}
