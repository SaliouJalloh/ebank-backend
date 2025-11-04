package org.msd.ebankingbackend.storage.factory;

import org.msd.ebankingbackend.storage.model.Account;
import org.msd.ebankingbackend.storage.model.CurrentAccount;

public class CurrentAccountFactory implements AccountFactory {

    @Override
    public Account createAccount() {
        return new CurrentAccount();
    }
}
