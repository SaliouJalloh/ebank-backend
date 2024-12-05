package org.msd.ebankingbackend.storage.persistences;

import org.msd.ebankingbackend.storage.models.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IOperationPersistenceService {
    Operation saveOperation(Operation operation);

    Page<Operation> findByAccountIdOrderByOperationDateDesc(Long accountId, PageRequest request);

    List<Operation> findAccountById(Long accountId);
}
