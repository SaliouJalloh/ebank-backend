package org.msd.ebankingbackend.domain.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.msd.ebankingbackend.infrastructure.persistence.ICustomerPersistenceService;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapperImpl;
import org.msd.ebankingbackend.infrastructure.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTestIT {

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

  /*  @Test
    void findCustomerById() {
        Customer customer = DataProviderTest.buildCustomer();

        // When
        Customer customer = customerPersistenceService.findCustomerById(1L);

        Customer savedCustomer;
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        assertThat(savedCustomer).usingRecursiveComparison().isEqualTo(customer);
    }*/

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