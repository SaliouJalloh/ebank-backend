package org.msd.ebankingbackend.domain.service;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Transaction;

public interface ITransactionService {
    Transaction save(Transaction transaction);

    List<Transaction> findAll();

    Transaction findById(Long id);

    List<Transaction> findAllByCustomerId(Long customerId);

    void delete(Long id);
}
