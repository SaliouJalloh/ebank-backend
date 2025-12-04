package org.msd.ebankingbackend.application.dto.projection;

import org.msd.ebankingbackend.infrastructure.enumeration.OperationType;
import java.time.LocalDateTime;

/**
 * DTO projection pour les listes d'opérations.
 */
public record OperationListProjection(
        Long id,
        double amount,
        OperationType type,
        LocalDateTime operationDate,
        Long accountId,
        Long customerId
) {
}
