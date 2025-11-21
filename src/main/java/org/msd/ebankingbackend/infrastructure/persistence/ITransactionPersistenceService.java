package org.msd.ebankingbackend.infrastructure.persistence;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Transaction;

public interface ITransactionPersistenceService {
    Transaction save(Transaction transaction);

    List<Transaction> findAll();

    Transaction findById(Long id);

    List<Transaction> findAllByCustomerId(Long userId);

    void delete(Long id);
}
