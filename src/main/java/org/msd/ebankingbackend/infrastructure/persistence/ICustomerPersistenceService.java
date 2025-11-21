package org.msd.ebankingbackend.infrastructure.persistence;

import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;

import java.util.List;

public interface ICustomerPersistenceService {

    Customer findCustomerById(Long id);

    List<Customer> findAllCustomers();

    Customer updateCustomer(Customer customer, Long id);

    void deleteCustomerById(Long id);

    Customer saveCustomerWithRole(RegisterRequest request);

    Customer saveCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    boolean existsCustomerByEmail(String email);

//    List<Customer> searchCustomer(String keyword);
}
