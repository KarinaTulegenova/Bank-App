package facade;

import base.Account;
import factory.AccountFactory;
import factory.BankFactory;
import builder.CardBuilder;

public class BankFacade implements Facade {
    private final AccountFactory factory = new BankFactory();

    private void announce(String msg) {
        System.out.println("\n" + msg);
    }

    @Override
    public Account openDeposit() {
        Account acc = new CardBuilder()
                .base(factory.createDeposit())
                .cashback(true)
                .build();
        announce("Congratulations! You opened a Deposit with 14% cashback.");
        return acc;
    }

    @Override
    public Account openBaseAccount() {
        Account acc = new CardBuilder()
                .base(factory.createBaseAccount())
                .build();
        announce("Congratulations! You opened a new card.");
        return acc;
    }

    @Override
    public Account openInvestmentAccount() {
        Account acc = new CardBuilder()
                .base(factory.createInvestmentAccount())
                .taxOptimize(true)
                .insurance(true)
                .build();
        announce("Congratulations! You opened a new Investment card with benefits.");
        return acc;
    }

    @Override
    public Account openKidsAccount() {
        Account acc = new CardBuilder()
                .base(factory.createBaseAccountKids()) 
                .kids(true)
                .build();
        announce("Congratulations! You opened a new Kids card.");
        return acc;
    }

    public void depositTo(Account account, double amount) { account.addMoney(amount); }

    public void payUtility(Account fromAccount, String name, double amount) {
        System.out.println("Paying for " + name + " from " + fromAccount.getClass().getSimpleName());
        fromAccount.withdraw(amount);
    }
    public void withdrawal(Account fromAccount, double amount) { fromAccount.withdraw(amount); }

    public void transfer(Account fromAccount, Account toAccount, double amount) { fromAccount.transferMoney(toAccount, amount); }

    public double viewCurrentBalance(Account account) { return account.viewCurrentBalance(); }

    public void investTo(Account fromAccount, double amount) {
        System.out.println("Invest in stocks!˚˖🦈˚˖✧˚.");
        fromAccount.invest(amount);
    }
    public void closeAccount(Account account) { account.closeAccount(); }

    public Account openCustom(Account base, boolean cashback, boolean insurance, boolean kids, boolean taxOptimize) {
        return new CardBuilder()
                .base(base)
                .cashback(cashback)
                .insurance(insurance)
                .kids(kids)
                .taxOptimize(taxOptimize)
                .build();
    }
}
