package org.msd.ebankingbackend.storage.persistences;

import org.msd.ebankingbackend.storage.models.Account;
import org.msd.ebankingbackend.storage.models.CurrentAccount;
import org.msd.ebankingbackend.storage.models.SavingAccount;

import java.util.List;

public interface IAccountPersistenceService {

    Account findAccountById(Long id);

    List<Account> findAllAccounts();

    Account saveAccount(Account Account);

    CurrentAccount saveCurrentAccount(CurrentAccount Account);

    SavingAccount saveSavingAccount(SavingAccount Account);

    boolean existAccountById(Long id);
}
