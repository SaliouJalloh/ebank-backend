package org.msd.ebankingbackend.infrastructure.persistence.repository;

import org.msd.ebankingbackend.infrastructure.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    /**
     * Projection JPA optimisée pour les listes de contacts.
     */
    @org.springframework.data.jpa.repository.Query("""
        SELECT new org.msd.ebankingbackend.application.dto.projection.ContactListProjection(
            c.id, c.firstname, c.lastname, c.email, c.iban, c.customer.id
        )
        FROM contact c
        """)
    java.util.List<org.msd.ebankingbackend.application.dto.projection.ContactListProjection> findAllProjected();
}
