package org.msd.ebankingbackend.service;

import org.msd.ebankingbackend.storage.models.Customer;

public interface ICustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);
}