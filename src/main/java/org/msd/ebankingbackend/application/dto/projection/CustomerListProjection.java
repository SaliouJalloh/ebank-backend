package org.msd.ebankingbackend.application.dto.projection;

/**
 * DTO projection pour les listes de customers.
 * Utilisé par les queries Repository pour éviter de charger toutes les relations.
 */
public record CustomerListProjection(
        Long id,
        String firstName,
        String lastName,
        String email,
        boolean active
) {
}
