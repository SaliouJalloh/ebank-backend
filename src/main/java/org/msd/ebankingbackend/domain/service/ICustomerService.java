package org.msd.ebankingbackend.domain.service;

import org.msd.ebankingbackend.domain.model.Customer;

import java.util.List;

public interface ICustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomers();

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    Customer update(Customer customer, Long id);

    void delete(Long id);

    List<Customer> searchCustomers(String keyword);
}
