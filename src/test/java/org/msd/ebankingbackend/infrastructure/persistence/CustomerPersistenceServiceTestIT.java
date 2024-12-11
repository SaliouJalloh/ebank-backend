package org.msd.ebankingbackend.infrastructure.persistence;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapperImpl;
import org.msd.ebankingbackend.infrastructure.persistence.repository.CustomerRepository;
import org.msd.ebankingbackend.tools.DataProviderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import({CustomerPersistenceService.class, ICustomerPersistenceMapperImpl.class})
public class CustomerPersistenceServiceTestIT {
    private final ICustomerPersistenceMapper persistenceMapper = new ICustomerPersistenceMapperImpl();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerPersistenceService customerPersistenceService;

    @BeforeEach
    void cleanData() {
        customerRepository.deleteAll();
        customerRepository.flush();
    }

    @Test
    void findById_OK() {

        //When
        Customer customer = customerPersistenceService.saveCustomer(DataProviderTest.buildCustomer());

        Customer savedUser = customerPersistenceService.findCustomerById(customer.getId());

        // Then
        assertThat(customer).isNotNull();
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(customer);
    }
}
