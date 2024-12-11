package org.msd.ebankingbackend.infrastructure.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CustomerEntity;
import org.msd.ebankingbackend.infrastructure.persistence.repository.CustomerRepository;
import org.msd.ebankingbackend.tools.DataProviderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTestIT {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void cleanData() {
        customerRepository.deleteAll();
        customerRepository.flush();
    }

    @Test
    void initially_empty() {
        // when
        long count = customerRepository.count();
        // then
        assertThat(count).isZero();
    }

    @Test
    void testFindById_Empty() {
        // When
        Optional<CustomerEntity> optionalUser = customerRepository.findById(1L);
        // Then
        assertThat(customerRepository.count()).isZero();
        assertThat(optionalUser).isEmpty();
    }

    @Test
    void testFindById_success() {
        // Given
        CustomerEntity customerEntity = DataProviderTest.buildCustomerEntity();
        customerRepository.save(customerEntity);

        // When
        Optional<CustomerEntity> foundCustomer = customerRepository.findById(customerEntity.getId());

        // Then
        assertThat(customerRepository.count()).isEqualTo(1);
        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get()).usingRecursiveComparison().isEqualTo(customerEntity);
    }
}
