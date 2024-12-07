package org.msd.ebankingbackend.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.service.ICustomerService;
import org.msd.ebankingbackend.service.validator.EntityValidatorService;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerPersistenceService customerPersistenceService;
    private final EntityValidatorService<Customer> validator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        validator.validateInput(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        if (customerPersistenceService.existsCustomerByEmail(customer.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exist");
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
    public Customer findCustomerById(Long id) {
        return customerPersistenceService.findCustomerById(id);
    }

    @Override
    public Customer update(Customer customer, Long id) {
        return customerPersistenceService.updateCustomer(customer, id);
    }

    @Override
    public void delete(Long id) {
        customerPersistenceService.deleteCustomerById(id);
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        return List.of();
    }
}
