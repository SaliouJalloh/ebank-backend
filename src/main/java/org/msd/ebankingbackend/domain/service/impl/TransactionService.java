package org.msd.ebankingbackend.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Transaction;
import org.msd.ebankingbackend.domain.service.ITransactionService;
import org.msd.ebankingbackend.infrastructure.persistence.service.ITransactionPersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionService implements ITransactionService {

    private final ITransactionPersistenceService persistenceService;

    @Override
    public Transaction save(Transaction transaction) {
        log.info("Saving transaction: {}", transaction);
        return persistenceService.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return persistenceService.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return persistenceService.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllByCustomerId(Long userId) {
        return persistenceService.findAllByCustomerId(userId);
    }

    @Override
    public void delete(Long id) {
        persistenceService.delete(id);
    }
}
