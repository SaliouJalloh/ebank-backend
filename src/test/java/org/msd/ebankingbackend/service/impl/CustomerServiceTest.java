package org.msd.ebankingbackend.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.msd.ebankingbackend.storage.mappers.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.storage.mappers.ICustomerPersistenceMapperImpl;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.msd.ebankingbackend.storage.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
@ActiveProfiles("test")
class CustomerServiceTest {

    private final ICustomerPersistenceMapper persistenceMapper = new ICustomerPersistenceMapperImpl();

    @Autowired
    ICustomerPersistenceService customerPersistenceService;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(customerPersistenceService, "customerRepository", customerRepository);
        ReflectionTestUtils.setField(customerPersistenceService, "persistenceMapper", persistenceMapper);

        customerRepository.deleteAll();
        customerRepository.flush();
    }


    @Test
    void saveCustomer() {
    }

    @Test
    void findCustomerByEmail() {
    }

    @Test
    void findCustomerById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}