package org.msd.ebankingbackend.tools;

import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CustomerEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.RoleEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.msd.ebankingbackend.infrastructure.persistence.enumeration.RoleName.ROLE_USER;


@ActiveProfiles("test")
public class DataProviderTest {

    public static CustomerEntity buildCustomerEntity() {
        return CustomerEntity.builder()
                .firstName("test")
                .lastName("Ba")
                .email("test@gmail.com")
                .password("test@224")
                .active(true)
                .role(createRoles())
                .build();
    }

    public static Customer buildCustomer() {
        return Customer.builder()
                .firstName("test")
                .lastName("Ba")
                .email("test@gmail.com")
                .password("test@224")
                .build();
    }

    public static RoleEntity createRoles() {
        return RoleEntity.builder()
                .name(ROLE_USER)
                .description("User who utilizes transportation services provided by drivers")
                .build();
    }
}
