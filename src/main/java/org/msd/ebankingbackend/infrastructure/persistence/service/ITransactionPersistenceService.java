package org.msd.ebankingbackend.infrastructure.persistence.service;

import org.msd.ebankingbackend.domain.model.Transaction;

import java.util.List;

public interface ITransactionPersistenceService {
    Transaction save(Transaction transaction);

    List<Transaction> findAll();

    Transaction findById(Long id);

    List<Transaction> findAllByCustomerId(Long userId);

    void delete(Long id);
}
