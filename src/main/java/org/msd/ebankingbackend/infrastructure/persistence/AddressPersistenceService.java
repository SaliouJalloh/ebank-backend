package org.msd.ebankingbackend.infrastructure.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.infrastructure.mapper.IAddressPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.entity.AddressEntity;
import org.msd.ebankingbackend.infrastructure.persistence.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressPersistenceService implements IAddressPersistenceService {

    private final IAddressPersistenceMapper persistenceMapper;
    private final AddressRepository repository;

    @Override
    public Address save(Address address) {
        AddressEntity addressEntity = persistenceMapper.toEntity(address);
        AddressEntity savedAddress = repository.save(addressEntity);
        return persistenceMapper.toModel(savedAddress);
    }

    @Override
    public List<Address> findAll() {
        var addressEntities = repository.findAll();
        return addressEntities.stream().map(persistenceMapper::toModel).toList();
    }

    @Override
    public Address findById(Long id) {
        AddressEntity addressEntity = repository.findById(id).orElseThrow(() -> {
            log.error("Address with id {} not found", id);
            return new EntityNotFoundException("Not address found with id: " + id);
        });
        return persistenceMapper.toModel(addressEntity);
    }

    @Override
    public void delete(Long id) {
        AddressEntity addressEntity = repository.findById(id).orElseThrow(() -> {
            log.error("Address with id {} not deleted, because it was not found.", id);
            return new UnsupportedOperationException("Not address found with id: " + id);
        });
        repository.delete(addressEntity);
    }
}
