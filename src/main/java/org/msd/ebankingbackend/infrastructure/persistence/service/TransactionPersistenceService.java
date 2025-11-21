package org.msd.ebankingbackend.infrastructure.persistence.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Transaction;
import org.msd.ebankingbackend.infrastructure.entity.TransactionEntity;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ITransactionPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionPersistenceService implements ITransactionPersistenceService {

    private final ITransactionPersistenceMapper mapper;
    private final TransactionRepository repository;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.toEntity(transaction);
        TransactionEntity savedTransaction = repository.save(transactionEntity);
        return mapper.toModel(savedTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        var transactions = repository.findAll();
        return transactions.stream().map(mapper::toModel).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        var transactionEntity = repository.findById(id).orElseThrow(() -> {
            log.error("Transaction with id {} not found", id);
            return new EntityNotFoundException("Not transaction found with id: " + id);
        });
        return mapper.toModel(transactionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllByCustomerId(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Not transaction found with id: " + id);
        }
        return repository.findAllByCustomerId(id).stream().map(mapper::toModel).toList();
    }

    @Override
    public void delete(Long id) {
        TransactionEntity transactionEntity = repository.findById(id).orElseThrow(() -> {
            log.error("Transaction with id {} not deleted, because it was not found.", id);
            return new EntityNotFoundException("Not transaction found with id: " + id);
        });
        repository.delete(transactionEntity);
    }
}
