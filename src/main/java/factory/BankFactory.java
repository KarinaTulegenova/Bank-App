package factory;

import base.Account;
import base.*;

public class BankFactory implements AccountFactory {
    @Override
    public Account createKaspiGold() {
        return new BaseAccount();
    }
    @Override
    public Account createKaspiGildKids() {
        return new BaseAccount();
    }
    @Override
    public Account createDeposit() {
        return new Deposit();
    }
    @Override
    public Account createKaspiInvestment() {
        return new InvestmentAccount();
    }
}
