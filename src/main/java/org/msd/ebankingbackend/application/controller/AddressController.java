package org.msd.ebankingbackend.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.request.AddressRequestDto;
import org.msd.ebankingbackend.application.dto.response.AddressResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
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
    public AddressResponseDto createAddress(@Valid @RequestBody AddressRequestDto addressRequestDto) {
        Address address = mapper.toAddress(addressRequestDto);
        Address saved = service.save(address);
        return mapper.toAddressResponseDto(saved);
    }

    @GetMapping
    public List<AddressResponseDto> findAll() {
        List<Address> addresses = service.findAll();
        return addresses.stream()
                .map(mapper::toAddressResponseDto)
                .toList();
    }

    @GetMapping("/{addressId}")
    public AddressResponseDto findById(@PathVariable("addressId") Long addressId) {
        Address address = service.findById(addressId);
        return mapper.toAddressResponseDto(address);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("addressId") Long addressId) {
        service.delete(addressId);
    }
}
