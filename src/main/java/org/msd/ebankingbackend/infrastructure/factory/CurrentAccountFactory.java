package org.msd.ebankingbackend.infrastructure.factory;

import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.CurrentAccount;

public class CurrentAccountFactory implements AccountFactory {

    @Override
    public Account createAccount() {
        return new CurrentAccount();
    }
}
