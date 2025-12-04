package org.msd.ebankingbackend.infrastructure.persistence.repository;

import org.msd.ebankingbackend.application.dto.projection.CustomerListProjection;
import org.msd.ebankingbackend.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    /**
     * Projection JPA optimisée pour les listes.
     * Retourne directement un DTO sans charger les relations → 0 mapping !
     */
    @org.springframework.data.jpa.repository.Query("""
        SELECT new org.msd.ebankingbackend.application.dto.projection.CustomerListProjection(
            c.id, c.firstName, c.lastName, c.email, c.active
        )
        FROM customers c
        """)
    java.util.List<CustomerListProjection> findAllProjected();

  /*  @Query("SELECT c FROM customer c WHERE c.name LIKE :kw")
    List<CustomerEntity> searchCustomer(@Param("kw") String keyword);*/
}
