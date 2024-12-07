package org.msd.ebankingbackend;

import org.msd.ebankingbackend.storage.entities.RoleEntity;
import org.msd.ebankingbackend.storage.enums.RoleName;
import org.msd.ebankingbackend.storage.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static org.msd.ebankingbackend.storage.enums.RoleName.ROLE_ADMIN;
import static org.msd.ebankingbackend.storage.enums.RoleName.ROLE_USER;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
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