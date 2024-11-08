package org.msd.ebankingbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.controller.dtos.CustomerDto;
import org.msd.ebankingbackend.controller.mappers.IControllerMapper;
import org.msd.ebankingbackend.service.ICustomerService;
import org.msd.ebankingbackend.storage.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final IControllerMapper controllerMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return controllerMapper.toCustomerDto(savedCustomer);
    }

    @PutMapping("/update/{id}")
    public CustomerDto getCustomerById(@RequestBody Customer customer, Long id) {
        Customer updatedCustomer = customerService.update(customer, id);
        return controllerMapper.toCustomerDto(updatedCustomer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
    }
}
