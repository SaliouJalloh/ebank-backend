package org.msd.ebankingbackend.infrastructure.persistence.repository;

import org.msd.ebankingbackend.infrastructure.entity.TransactionEntity;
import org.msd.ebankingbackend.infrastructure.enumeration.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByCustomerId(Long customerId);

    List<TransactionEntity> findAllByCustomerIdAndDateTimeBetween(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionEntity> findAllByCustomerIdAndType(Long customerId, TransactionType type);

    @Query("SELECT t FROM TransactionEntity t WHERE t.customer.id = :customerId AND t.type = :type ORDER BY t.amount DESC LIMIT 1")
    Optional<TransactionEntity> findHighestAmountByUserIdAndType(@Param("customerId") Long customerId, @Param("type") TransactionType type);

    @Query("SELECT t.dateTime as date, SUM(t.amount) as total FROM TransactionEntity t " +
            "WHERE t.customer.id = :customerId " +
            "AND t.dateTime BETWEEN :startDate AND :endDate " +
            "GROUP BY t.dateTime ORDER BY t.dateTime")
    List<Map<String, Object>> findDailySumByUserIdAndDateRange(
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT SUM(CASE WHEN t.type = 'CREDIT' THEN t.amount ELSE -t.amount END) " +
            "FROM TransactionEntity t WHERE t.customer.id = :customerId")
    Double calculateBalance(@Param("customerId") Long customerId);

    /**
     * Projection JPA optimisée pour les listes de transactions.
     */
    @Query("""
        SELECT new org.msd.ebankingbackend.application.dto.projection.TransactionListProjection(
            t.id, t.amount, t.type, t.dateTime, t.destinationIban, t.customer.id
        )
        FROM TransactionEntity t
        """)
    List<org.msd.ebankingbackend.application.dto.projection.TransactionListProjection> findAllProjected();
}
