package decorators;

import base.Account;

public class InsuranceDecorator extends AccountDecorator {
    public InsuranceDecorator(Account account) {
        super(account);
        System.out.println("Insurance package successfully activated for this account");
    }

    @Override
    public void closeAccount() {
        System.out.println("Insurance benefits settled. Account closed securely");
        super.closeAccount();
    }
}
