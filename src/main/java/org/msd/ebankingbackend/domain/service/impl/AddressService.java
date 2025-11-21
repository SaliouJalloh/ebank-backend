package org.msd.ebankingbackend.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.domain.service.IAddressService;
import org.msd.ebankingbackend.domain.service.validator.EntityValidatorService;
import org.msd.ebankingbackend.infrastructure.persistence.IAddressPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService implements IAddressService {

    private final IAddressPersistenceService persistenceService;
    private final EntityValidatorService<Address> validator;

    @Override
    public Address save(Address address) {
        log.info("Saving address: {}", address);
        validator.validateInput(address);
        return persistenceService.save(address);
    }

    @Override
    public List<Address> findAll() {
        return persistenceService.findAll();
    }

    @Override
    public Address findById(Long id) {
        return persistenceService.findById(id);
    }

    @Override
    public void delete(Long id) {
        persistenceService.delete(id);
    }
}
