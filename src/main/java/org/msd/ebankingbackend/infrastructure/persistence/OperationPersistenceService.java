package org.msd.ebankingbackend.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Operation;
import org.msd.ebankingbackend.infrastructure.persistence.entity.OperationEntity;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.IOperationPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.repository.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class OperationPersistenceService implements IOperationPersistenceService {

    private final OperationRepository operationRepository;
    private final IOperationPersistenceMapper operationPersistenceMapper;

    @Override
    public Operation saveOperation(Operation operation) {
        OperationEntity operationEntity = operationPersistenceMapper.toEntity(operation);
        OperationEntity savedOperation = operationRepository.save(operationEntity);
        return operationPersistenceMapper.toModel(savedOperation);
    }

    @Override
    public Page<Operation> findByAccountIdOrderByOperationDateDesc(Long accountId, PageRequest request) {
        return null;
    }

    @Override
    public List<Operation> findAccountById(Long accountId) {
        var operations = operationRepository.findAccountById(accountId);
        return operations.stream().map(operationPersistenceMapper::toModel).toList();
    }
}
