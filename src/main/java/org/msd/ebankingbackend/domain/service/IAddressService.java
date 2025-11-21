package org.msd.ebankingbackend.domain.service;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Address;

public interface IAddressService {
    Address save(Address address);

    List<Address> findAll();

    Address findById(Long id);

    void delete(Long id);
}
