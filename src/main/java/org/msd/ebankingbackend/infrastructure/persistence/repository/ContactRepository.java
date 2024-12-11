package org.msd.ebankingbackend.infrastructure.persistence.repository;

import org.msd.ebankingbackend.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

}