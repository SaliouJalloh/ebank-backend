package org.msd.ebankingbackend.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.msd.ebankingbackend.api.dto.AddressDto;
import org.msd.ebankingbackend.api.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.domain.service.IAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@Tag(name = "Addresses", description = "API de gestion des adresses")
public class AddressController {

    private final IAddressService service;
    private final IControllerMapper mapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto save(@Valid @RequestBody Address address) {
        Address saved = service.save(address);
        return mapper.toAddressDto(saved);
    }

    @GetMapping
    public List<AddressDto> findAll() {
        List<Address> addresses = service.findAll();
        return addresses.stream()
                .map(mapper::toAddressDto)
                .toList();
    }

    @GetMapping("/{addressId}")
    public AddressDto findById(@PathVariable("addressId") Long addressId) {
        Address address = service.findById(addressId);
        return mapper.toAddressDto(address);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("addressId") Long addressId) {
        service.delete(addressId);
    }
}
