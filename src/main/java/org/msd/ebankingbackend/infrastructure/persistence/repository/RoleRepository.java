package org.msd.ebankingbackend.infrastructure.persistence.repository;

import org.msd.ebankingbackend.infrastructure.entity.RoleEntity;
import org.msd.ebankingbackend.infrastructure.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName name);
}
