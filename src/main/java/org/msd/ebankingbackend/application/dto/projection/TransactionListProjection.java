package org.msd.ebankingbackend.application.dto.projection;

import org.msd.ebankingbackend.infrastructure.enumeration.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO projection pour les listes de transactions.
 */
public record TransactionListProjection(
        Long id,
        BigDecimal amount,
        TransactionType type,
        LocalDateTime dateTime,
        String destinationIban,
        Long customerId
) {
}
