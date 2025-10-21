package factory;
import base.*;

public interface AccountFactory {
    Account createKaspiGold();
    Account createKaspiGildKids();
    Account createDeposit();
    Account createKaspiInvestment();
}
