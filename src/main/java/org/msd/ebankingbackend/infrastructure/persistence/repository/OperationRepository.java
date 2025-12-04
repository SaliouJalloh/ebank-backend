package org.msd.ebankingbackend.infrastructure.persistence.repository;

import org.msd.ebankingbackend.infrastructure.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findAccountById(Long accountId);

    Page<OperationEntity> findAccountByIdOrderByOperationDateDesc(Long accountId, Pageable pageable);

    /**
     * Projection JPA optimisée pour les listes d'opérations.
     */
    @org.springframework.data.jpa.repository.Query("""
        SELECT new org.msd.ebankingbackend.application.dto.projection.OperationListProjection(
            o.id, o.amount, o.type, o.operationDate, o.account.id, o.customer.id
        )
        FROM operation o
        """)
    java.util.List<org.msd.ebankingbackend.application.dto.projection.OperationListProjection> findAllProjected();
}
