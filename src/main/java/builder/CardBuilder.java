package builder;

import base.Account;
import decorators.*;

public class CardBuilder {
    private Account base;
    private boolean cashback;
    private boolean insurance;
    private boolean kids;
    private boolean taxOptimize;

    public CardBuilder base(Account account) {
        this.base = account;
        return this;
    }

    public CardBuilder cashback(boolean enabled) {
        this.cashback = enabled;
        return this;
    }

    public CardBuilder insurance(boolean enabled) {
        this.insurance = enabled;
        return this;
    }

    public CardBuilder kids(boolean enabled) {
        this.kids = enabled;
        return this;
    }

    public CardBuilder taxOptimize(boolean enabled) {
        this.taxOptimize = enabled;
        return this;
    }

    public Account build() {
        if (base == null) throw new IllegalStateException("Base account is required");
        Account acc = base;
        if (taxOptimize) acc = new TaxOptimizerDecorator(acc);
        if (insurance)   acc = new InsuranceDecorator(acc);
        if (kids)        acc = new KidsCardDecorator(acc);
        if (cashback)    acc = new CashBackDecorator(acc);
        return acc;
    }
}