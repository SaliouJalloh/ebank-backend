package org.msd.ebankingbackend.domain.service;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Customer;

public interface ICustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomers();

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    Customer updateCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);

    List<Customer> searchCustomers(String keyword);

    Long validateAccount(Long userId);

    Long invalidateAccount(Long userId);
}
