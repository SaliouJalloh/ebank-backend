package org.msd.ebankingbackend.infrastructure.persistence.enumeration;

import lombok.Getter;

@Getter
public enum RoleName {

    ROLE_ADMIN("ADMIN", "User with administrative privileges and full access to the system"),
    ROLE_USER("USER", "User who provides services to bank");

    private final String name;

    private final String description;

    RoleName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
