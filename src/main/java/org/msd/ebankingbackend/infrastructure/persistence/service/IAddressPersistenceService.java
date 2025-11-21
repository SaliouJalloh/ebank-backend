package org.msd.ebankingbackend.infrastructure.persistence.service;

import org.msd.ebankingbackend.domain.model.Address;

import java.util.List;

public interface IAddressPersistenceService {
    Address save(Address address);

    List<Address> findAll();

    Address findById(Long id);

    void delete(Long id);
}
