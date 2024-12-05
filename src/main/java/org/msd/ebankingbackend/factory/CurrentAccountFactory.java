package org.msd.ebankingbackend.factory;

import org.msd.ebankingbackend.storage.models.Account;
import org.msd.ebankingbackend.storage.models.CurrentAccount;

public class CurrentAccountFactory implements AccountFactory {
    
    @Override
    public Account createAccount() {
        return new CurrentAccount();
    }
}
