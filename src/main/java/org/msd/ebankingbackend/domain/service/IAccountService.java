package org.msd.ebankingbackend.domain.service;

import java.util.List;

import org.msd.ebankingbackend.domain.model.*;

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

    void deleteAccount(Long accountId);

//    List<Customer> searchCustomers(String keyword);
}
