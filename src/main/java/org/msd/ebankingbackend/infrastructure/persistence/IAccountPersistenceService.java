package org.msd.ebankingbackend.infrastructure.persistence;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.CurrentAccount;
import org.msd.ebankingbackend.domain.model.SavingAccount;

public interface IAccountPersistenceService {

    Account findAccountById(Long id);

    List<Account> findAllAccounts();

    Account saveAccount(Account Account);

    CurrentAccount saveCurrentAccount(CurrentAccount Account);

    SavingAccount saveSavingAccount(SavingAccount Account);

    boolean existAccountById(Long id);

    void deleteAccount(Long id);
}
