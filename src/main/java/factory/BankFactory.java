package factory;

import base.Account;
import base.*;

public class BankFactory implements AccountFactory {
    @Override
    public Account createBaseAccount() {
        return new BaseAccount();
    }
    @Override
    public Account createBaseAccountKids() {
        return new BaseAccount();
    }
    @Override
    public Account createDeposit() {
        return new Deposit();
    }
    @Override
    public Account createInvestmentAccount() {
        return new InvestmentAccount();
    }
}
