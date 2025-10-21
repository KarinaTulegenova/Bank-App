package facade;

import base.Account;

public interface Facade {
    Account openDeposit();
    Account openBaseAccount();
    Account openInvestmentAccount();
    Account openKidsAccount();

    void depositTo(Account acc, double amount);
    void withdrawal(Account acc, double amount);
    void investTo(Account acc, double amount);
    void transfer(Account from, Account to, double amount);
    void payUtility(Account acc, String name, double amount);
    void closeAccount(Account acc);
    double viewCurrentBalance(Account acc);
}
