package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findAccountById(Long accountId);

    Page<OperationEntity> findAccountByIdOrderByOperationDateDesc(Long accountId, Pageable pageable);
}
