package org.msd.ebankingbackend.storage.persistence;

import org.msd.ebankingbackend.storage.model.Account;
import org.msd.ebankingbackend.storage.model.CurrentAccount;
import org.msd.ebankingbackend.storage.model.SavingAccount;

import java.util.List;

public interface IAccountPersistenceService {

    Account findAccountById(Long id);

    List<Account> findAllAccounts();

    Account saveAccount(Account Account);

    CurrentAccount saveCurrentAccount(CurrentAccount Account);

    SavingAccount saveSavingAccount(SavingAccount Account);

    boolean existAccountById(Long id);
}
