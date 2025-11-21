package org.msd.ebankingbackend.infrastructure.persistence;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.msd.ebankingbackend.config.TestMapperConfiguration;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.infrastructure.persistence.repository.CustomerRepository;
import org.msd.ebankingbackend.infrastructure.persistence.service.CustomerPersistenceService;
import org.msd.ebankingbackend.tools.DataProviderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import({CustomerPersistenceService.class, TestMapperConfiguration.class})
class CustomerPersistenceServiceITTest {

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
        // Given
        Customer customerToSave = DataProviderTest.buildCustomer();

        // When
        Customer customer = customerPersistenceService.saveCustomer(customerToSave);
        Customer savedCustomer = customerPersistenceService.findCustomerById(customer.getId());

        // Then
        assertThat(customer).isNotNull();
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isEqualTo(customer.getId());
        assertThat(savedCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(savedCustomer.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(savedCustomer.getLastName()).isEqualTo(customer.getLastName());
    }
}
