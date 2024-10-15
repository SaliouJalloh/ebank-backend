package org.msd.ebankingbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.service.ICustomerService;
import org.msd.ebankingbackend.storage.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}