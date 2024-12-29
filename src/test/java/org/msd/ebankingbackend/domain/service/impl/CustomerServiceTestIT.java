package org.msd.ebankingbackend.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.infrastructure.persistence.CustomerPersistenceService;
import org.msd.ebankingbackend.infrastructure.persistence.ICustomerPersistenceService;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CustomerEntity;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapperImpl;
import org.msd.ebankingbackend.infrastructure.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
@Import({CustomerPersistenceService.class, ICustomerPersistenceMapperImpl.class})// Add the mapper manually
@Transactional // Assure que chaque test est isolé et rollback après exécution
public class CustomerServiceTestIT {

    @Autowired
    private ICustomerPersistenceMapper persistenceMapper;

    @Autowired
    private ICustomerPersistenceService customerPersistenceService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        // Clear the database before each test
        customerRepository.deleteAll();
        customerRepository.flush();
    }

    @Test
    void findCustomerById_shouldReturnCustomer_whenExists() {
        // Given: Insert a customer into the database
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(1L)
                .email("test@gmail.com")
                .build();
        customerEntity = customerRepository.save(customerEntity);

        // When: Calling the service method
        Customer customer = customerPersistenceService.findCustomerById(customerEntity.getId());

        // When: Calling the service method
        assertThat(customer).isNotNull();
        assertThat(customer).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void findCustomerById_ShouldThrowException_WhenCustomerDoesNotExist() {
        // Given: No customer in the database

        // When & Then: Expect an exception when calling the method with a non-existent ID
        Long nonExistentId = 999L;
        assertThatThrownBy(() -> customerPersistenceService.findCustomerById(nonExistentId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Not customer found with id: " + nonExistentId);
    }

    /*@Test
    void saveCustomer() {
    }

    @Test
    void findCustomerByEmail() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }*/
}