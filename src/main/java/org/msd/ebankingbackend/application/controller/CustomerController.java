package org.msd.ebankingbackend.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.request.CustomerRequestDto;
import org.msd.ebankingbackend.application.dto.request.UpdateCustomerRequestDto;
import org.msd.ebankingbackend.application.dto.response.CustomerResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
@Tag(name = "Customers", description = "API de gestion des clients")
public class CustomerController {

    private final ICustomerService customerService;
    private final IControllerMapper mapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        Customer customer = mapper.toCustomer(customerRequestDto);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return mapper.toCustomerResponseDto(savedCustomer);
    }

    @GetMapping
    public List<CustomerResponseDto> getCustomers() {
        List<Customer> allCustomers = customerService.findAllCustomers();
        return allCustomers.stream()
                .map(mapper::toCustomerResponseDto)
                .toList();
    }

    @GetMapping("/{customerId}")
    public CustomerResponseDto getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return mapper.toCustomerResponseDto(customer);
    }

    @PutMapping("/{customerId}")
    public CustomerResponseDto updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
        Customer customer = mapper.toCustomer(updateCustomerRequestDto);
        Customer updatedCustomer = customerService.updateCustomer(customer, customerId);
        return mapper.toCustomerResponseDto(updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
