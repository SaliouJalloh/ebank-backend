package org.msd.ebankingbackend.storage.factory;

import org.msd.ebankingbackend.storage.model.Account;
import org.msd.ebankingbackend.storage.model.SavingAccount;

public class SavingAccountFactory implements AccountFactory {

    @Override
    public Account createAccount() {
        return new SavingAccount();
    }
}
