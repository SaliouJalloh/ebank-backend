package org.msd.ebankingbackend.infrastructure.persistence;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Address;

public interface IAddressPersistenceService {
    Address save(Address address);

    List<Address> findAll();

    Address findById(Long id);

    void delete(Long id);
}
