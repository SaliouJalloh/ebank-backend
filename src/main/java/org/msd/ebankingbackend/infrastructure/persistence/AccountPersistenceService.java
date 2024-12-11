package org.msd.ebankingbackend.infrastructure.persistence;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.CurrentAccount;
import org.msd.ebankingbackend.domain.model.SavingAccount;
import org.msd.ebankingbackend.infrastructure.persistence.entity.AccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CurrentAccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.SavingAccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.IAccountPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountPersistenceService implements IAccountPersistenceService {

    private final IAccountPersistenceMapper accountPersistenceMapper;
    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAllAccounts() {
        var accounts = accountRepository.findAll();
        return accounts.stream().map(accountEntity -> {
            if (accountEntity instanceof SavingAccountEntity savingAccountEntity) {
                return accountPersistenceMapper.toModel(savingAccountEntity);
            } else {
                CurrentAccountEntity currentAccountEntity = (CurrentAccountEntity) accountEntity;
                return accountPersistenceMapper.toModel(currentAccountEntity);
            }
        }).toList();
    }

    @Override
    public Account findAccountById(Long id) {
        var accountEntity = accountRepository.findById(id).orElseThrow(() -> {
            log.error("Account with id {} not found", id);
            return new EntityNotFoundException("Not account found with id: " + id);
        });
        if (accountEntity instanceof SavingAccountEntity savingAccountEntity) {
            return accountPersistenceMapper.toModel(savingAccountEntity);
        } else {
            CurrentAccountEntity currentAccountEntity = (CurrentAccountEntity) accountEntity;
            return accountPersistenceMapper.toModel(currentAccountEntity);
        }
    }

    @Override
    public Account saveAccount(Account account) {
        AccountEntity accountEntity = accountPersistenceMapper.toEntity(account);
        AccountEntity savedAccount = accountRepository.save(accountEntity);
        return accountPersistenceMapper.toModel(savedAccount);
    }

    @Override
    public CurrentAccount saveCurrentAccount(CurrentAccount account) {
        CurrentAccountEntity accountEntity = accountPersistenceMapper.toEntity(account);
        CurrentAccountEntity savedAccount = accountRepository.save(accountEntity);
        return accountPersistenceMapper.toModel(savedAccount);
    }

    @Override
    public SavingAccount saveSavingAccount(SavingAccount account) {
        SavingAccountEntity accountEntity = accountPersistenceMapper.toEntity(account);
        SavingAccountEntity savedAccount = accountRepository.save(accountEntity);
        return accountPersistenceMapper.toModel(savedAccount);
    }

    @Override
    public boolean existAccountById(Long id) {
        return accountRepository.existsById(id);
    }
}
