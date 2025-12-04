package org.msd.ebankingbackend.domain.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.msd.ebankingbackend.domain.exception.CustomerAlreadyExistsException;
import org.msd.ebankingbackend.domain.exception.CustomerNotFoundException;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.ICustomerService;
import org.msd.ebankingbackend.domain.service.validator.EntityValidatorService;
import org.msd.ebankingbackend.infrastructure.persistence.service.ICustomerPersistenceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerPersistenceService customerPersistenceService;
    private final EntityValidatorService<Customer> validator;
    private final PasswordEncoder passwordEncoder;

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        validator.validateInput(customer);
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        if (customerPersistenceService.existsCustomerByEmail(customer.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer already exist");
        }
        return customerPersistenceService.saveCustomer(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerPersistenceService.findAllCustomers();
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerPersistenceService.findCustomerByEmail(email);
    }

    @Override
    @Cacheable(value = "customers", key = "#id")
    public Customer findCustomerById(Long id) {
        return customerPersistenceService.findCustomerById(id);
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public Customer updateCustomer(Customer customer, Long id) {
        return customerPersistenceService.updateCustomer(customer, id);
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public void deleteCustomer(Long id) {
        customerPersistenceService.deleteCustomerById(id);
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        return List.of();
    }

    @Override
    public Long validateAccount(Long userId) {
        Customer customer = customerPersistenceService.findCustomerById(userId.longValue());
        if (customer == null) {
            throw new CustomerNotFoundException("Client non trouvé");
        }
        customer.setActive(true);
        customerPersistenceService.updateCustomer(customer, userId.longValue());
        return userId;
    }

    @Override
    public Long invalidateAccount(Long userId) {
        Customer customer = customerPersistenceService.findCustomerById(userId.longValue());
        if (customer == null) {
            throw new CustomerNotFoundException("Client non trouvé");
        }
        customer.setActive(false);
        customerPersistenceService.updateCustomer(customer, userId.longValue());
        return userId;
    }
}
