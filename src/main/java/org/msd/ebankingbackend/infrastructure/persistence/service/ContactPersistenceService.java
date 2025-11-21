package org.msd.ebankingbackend.infrastructure.persistence.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.infrastructure.entity.ContactEntity;
import org.msd.ebankingbackend.infrastructure.persistence.mapper.IContactPersistenceMapper;
import org.msd.ebankingbackend.infrastructure.persistence.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactPersistenceService implements IContactPersistenceService {

    private final IContactPersistenceMapper persistenceMapper;
    private final ContactRepository repository;

    @Override
    public Contact save(Contact contact) {
        ContactEntity contactEntity = persistenceMapper.toEntity(contact);
        ContactEntity savedContact = repository.save(contactEntity);
        return persistenceMapper.toModel(savedContact);
    }

    @Override
    public Contact findById(Long id) {
        var contactEntity = repository.findById(id).orElseThrow(() -> {
            log.error("Contact with id {} not found", id);
            return new EntityNotFoundException("Not contact found with id: " + id);
        });
        return persistenceMapper.toModel(contactEntity);
    }

    @Override
    public List<Contact> findAll() {
        List<ContactEntity> contactEntities = repository.findAll();
        return contactEntities.stream().map(persistenceMapper::toModel).toList();
    }

    @Override
    public List<Contact> findAllByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        ContactEntity contactEntity = repository.findById(id).orElseThrow(() -> {
            log.error("Contact with id {} not deleted, because it was not found.", id);
            return new UnsupportedOperationException("Not contact found with id: " + id);
        });
        repository.delete(contactEntity);
    }
}
