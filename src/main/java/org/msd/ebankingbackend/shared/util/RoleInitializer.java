package org.msd.ebankingbackend.shared.util;

import org.msd.ebankingbackend.infrastructure.persistence.entity.RoleEntity;
import org.msd.ebankingbackend.infrastructure.persistence.enumeration.RoleName;
import org.msd.ebankingbackend.infrastructure.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.msd.ebankingbackend.infrastructure.persistence.enumeration.RoleName.ROLE_ADMIN;
import static org.msd.ebankingbackend.infrastructure.persistence.enumeration.RoleName.ROLE_USER;

@Component
@Profile("dev")  // Ne s'exécutera qu'en mode développement
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        addRoleIfNotExists(ROLE_USER);
        addRoleIfNotExists(ROLE_ADMIN);
    }

    private void addRoleIfNotExists(RoleName roleName) {
        roleRepository.findByName(roleName).orElseGet(() -> {
            RoleEntity role = new RoleEntity();
            role.setName(roleName);
            return roleRepository.save(role);
        });
    }
}