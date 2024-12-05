package org.msd.ebankingbackend.factory;

import org.msd.ebankingbackend.storage.models.Account;
import org.msd.ebankingbackend.storage.models.SavingAccount;

public class SavingAccountFactory implements AccountFactory {
    
    @Override
    public Account createAccount() {
        return new SavingAccount();
    }
}
