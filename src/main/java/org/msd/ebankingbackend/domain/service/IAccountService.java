package org.msd.ebankingbackend.domain.service;

import org.msd.ebankingbackend.domain.model.*;

import java.util.List;

public interface IAccountService {

    Account saveAccount(Account account);

    List<Account> findAllAccounts();

    Account findAccountById(Long accountId);

    CurrentAccount saveCurrentAccount(double initialBalance, double overDraft, Long customerId);

    SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId);

    void debit(Long accountId, double amount);

    void credit(Long accountId, double amount);

    void transfer(Long accountIdSource, Long accountIdDestination, double amount);

    List<Operation> accountHistory(Long accountId);

    AccountHistory getAccountHistory(Long accountId, int page, int size);

//    List<Customer> searchCustomers(String keyword);
}
