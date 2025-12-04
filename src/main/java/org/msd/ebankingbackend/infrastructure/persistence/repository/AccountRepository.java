package org.msd.ebankingbackend.infrastructure.persistence.repository;


import java.util.List;

import org.msd.ebankingbackend.application.dto.projection.AccountListProjection;
import org.msd.ebankingbackend.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    /**
     * Projection JPA optimisée pour les listes de comptes.
     */
    @Query("""
        SELECT new org.msd.ebankingbackend.application.dto.projection.AccountListProjection(
            a.id, a.balance, a.createdAt, a.status, a.customer.id
        )
        FROM accounts a
        """)
    List<AccountListProjection> findAllProjected();
}
