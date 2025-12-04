package org.msd.ebankingbackend.application.dto.projection;

/**
 * DTO projection pour les listes de contacts.
 */
public record ContactListProjection(
        Long id,
        String firstname,
        String lastname,
        String email,
        String iban,
        Long customerId
) {
}
