package org.msd.ebankingbackend.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.*;
import org.msd.ebankingbackend.domain.service.IAccountService;
import org.msd.ebankingbackend.domain.service.validator.EntityValidatorService;
import org.msd.ebankingbackend.infrastructure.persistence.IAccountPersistenceService;
import org.msd.ebankingbackend.infrastructure.persistence.ICustomerPersistenceService;
import org.msd.ebankingbackend.infrastructure.persistence.IOperationPersistenceService;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.IOperationPersistenceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.msd.ebankingbackend.infrastructure.persistence.enumeration.OperationType.CREDIT;
import static org.msd.ebankingbackend.infrastructure.persistence.enumeration.OperationType.DEBIT;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements IAccountService {

    private final EntityValidatorService<Account> validator;
    private final IAccountPersistenceService accountPersistenceService;
    private final IOperationPersistenceService operationPersistenceService;
    private final ICustomerPersistenceService customerPersistenceService;
    private final IOperationPersistenceMapper operationPersistenceMapper;

    @Override
    public Account saveAccount(Account account) {
        log.info("Account saved: {}", account);
        validator.validateInput(account);
        if (accountPersistenceService.existAccountById(account.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exist");
        }
        return accountPersistenceService.saveAccount(account);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountPersistenceService.findAllAccounts();
    }

    @Override
    public Account findAccountById(Long accountId) {
        return accountPersistenceService.findAccountById(accountId);
    }

    @Override
    public CurrentAccount saveCurrentAccount(double initialBalance, double overDraft, Long customerId) {
        Customer customer = customerPersistenceService.findCustomerById(customerId);
        CurrentAccount currentAccount = CurrentAccount.builder()
                .balance(initialBalance)
                .overDraft(overDraft)
                .customer(customer)
                .build();
        return accountPersistenceService.saveCurrentAccount(currentAccount);
    }

    @Override
    public SavingAccount saveSavingAccount(double initialBalance, double interestRate, Long customerId) {
        Customer customer = customerPersistenceService.findCustomerById(customerId);
        SavingAccount savingAccount = SavingAccount.builder()
                .balance(initialBalance)
                .interestRate(interestRate)
                .customer(customer)
                .build();
        return accountPersistenceService.saveSavingAccount(savingAccount);
    }

    @Override
    public void debit(Long accountId, double amount) {
        Account account = accountPersistenceService.findAccountById(accountId);

        if (account.getBalance() < amount) {
            log.error("Balance not sufficient");
        }
        Operation operation = Operation.builder()
                .type(DEBIT)
                .amount(amount)
                .operationDate(now())
                .account(account)
                .build();

        operationPersistenceService.saveOperation(operation);
        account.setBalance(account.getBalance() - amount);
        accountPersistenceService.saveAccount(account);
    }

    @Override
    public void credit(Long accountId, double amount) {
        Account account = accountPersistenceService.findAccountById(accountId);
        Operation operation = Operation.builder()
                .type(CREDIT)
                .amount(amount)
                .operationDate(now())
                .account(account)
                .build();

        operationPersistenceService.saveOperation(operation);
        account.setBalance(account.getBalance() + amount);
        accountPersistenceService.saveAccount(account);
    }

    @Override
    public void transfer(Long accountIdSource, Long accountIdDestination, double amount) {
        debit(accountIdSource, amount);
        credit(accountIdSource, amount);
    }

    @Override
    public List<Operation> accountHistory(Long accountId) {
        return operationPersistenceService.findAccountById(accountId);
    }

    @Override
    public AccountHistory getAccountHistory(Long accountId, int page, int size) {
     /*   Account account = accountPersistenceService.findAccountById(accountId);
        Page<Operation> accountOperations = operationPersistenceService.findByAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistory accountHistory = new AccountHistory();
        List<Operation> accountOperationDTOS = accountOperations
                .getContent()
                .stream()
                .map(operationPersistenceMapper::toModel)
                .toList();
        accountHistory.setOperations(accountOperationDTOS);
        accountHistory.setBalance(account.getBalance());
        accountHistory.setCurrentPage(page);
        accountHistory.setPageSize(size);
        accountHistory.setTotalPages(accountOperations.getTotalPages());*/
        return null;
    }

/*    @Override
    public List<Customer> searchCustomers(String keyword) {
        return customerPersistenceService.searchCustomer(keyword);
    }*/
}
