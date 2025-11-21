package org.msd.ebankingbackend.infrastructure.persistence.repository;


import org.msd.ebankingbackend.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
