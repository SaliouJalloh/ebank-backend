package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByEmail(String email);

    boolean existsByEmail(String email);

  /*  @Query("SELECT c FROM customer c WHERE c.name LIKE :kw")
    List<CustomerEntity> searchCustomer(@Param("kw") String keyword);*/
}
