package org.msd.ebankingbackend.application.dto.projection;

import org.msd.ebankingbackend.infrastructure.enumeration.AccountStatus;

import java.time.LocalDateTime;

/**
 * DTO projection pour les listes de comptes.
 * Évite de charger les opérations et autres relations.
 */
public record AccountListProjection(
        Long id,
        Double balance,
        LocalDateTime createdAt,
        AccountStatus status,
        Long customerId
) {
}
