package org.msd.ebankingbackend.infrastructure.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CustomerEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.RoleEntity;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.repository.CustomerRepository;
import org.msd.ebankingbackend.infrastructure.persistence.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.msd.ebankingbackend.infrastructure.persistence.enumeration.RoleName.ROLE_USER;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerPersistenceService implements ICustomerPersistenceService {

    private final ICustomerPersistenceMapper customerPersistenceMapper;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    public Customer saveCustomerWithRole(RegisterRequest request) {
        RoleEntity role = roleRepository.findByName(ROLE_USER).orElseThrow(() -> {
            log.error("Role with name {} not found", ROLE_USER);
            return new EntityNotFoundException("Not role found with name: " + ROLE_USER);
        });
        CustomerEntity customerEntity = customerPersistenceMapper.toEntity(request);
        customerEntity.setRole(role);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return customerPersistenceMapper.toModel(savedCustomer);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerPersistenceMapper.toEntity(customer);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return customerPersistenceMapper.toModel(savedCustomer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email).orElseThrow(() -> {
            log.error("Customer with email {} not found", email);
            return new EntityNotFoundException("Not customer found with email: " + email);
        });
        return customerPersistenceMapper.toModel(customerEntity);
    }

    @Override
    public boolean existsCustomerByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

/*
    @Override
    public List<Customer> searchCustomer(String keyword) {
        List<CustomerEntity> customerEntities = customerRepository.searchCustomer(keyword);
        return customerEntities.stream().map(customerPersistenceMapper::toModel).toList();
    }
*/

    @Override
    public Customer findCustomerById(Long id) {
        CustomerEntity userEntity = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Customer with id {} not found", id);
                    return new EntityNotFoundException("Not customer found with id: " + id);
                });
        return customerPersistenceMapper.toModel(userEntity);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(customerPersistenceMapper::toModel).toList();
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        CustomerEntity updateCustomer = customerRepository.findById(id).orElseThrow(() -> {
            log.error("Customer with id {} not updated, because it was not found.", id);
            return new EntityNotFoundException("Not customer found with id: " + id);
        });
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setEmail(customer.getEmail());
        customerRepository.save(updateCustomer);
        return customerPersistenceMapper.toModel(updateCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        CustomerEntity customer = customerRepository.findById(id).orElseThrow(() -> {
            log.error("Customer with id {} not deleted, because it was not found.", id);
            return new EntityNotFoundException("Customer not found with id: " + id);
        });
        customerRepository.deleteById(customer.getId());
    }

}
