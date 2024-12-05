package org.msd.ebankingbackend.storage.persistences;

import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.storage.models.Customer;

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
