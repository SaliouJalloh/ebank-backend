package org.msd.ebankingbackend.domain.factory;

import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.SavingAccount;

public class SavingAccountFactory implements AccountFactory {

    @Override
    public Account createAccount() {
        return new SavingAccount();
    }
}
