package org.msd.ebankingbackend.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.domain.service.IContactService;
import org.msd.ebankingbackend.domain.service.validator.EntityValidatorService;
import org.msd.ebankingbackend.infrastructure.persistence.service.IContactPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService implements IContactService {

    private final IContactPersistenceService persistenceService;
    private final EntityValidatorService<Contact> validator;

    @Override
    public Contact save(Contact contact) {
        log.info("Contact saved: {}", contact);
        validator.validateInput(contact);
        return persistenceService.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return persistenceService.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return persistenceService.findById(id);
    }

    @Override
    public List<Contact> findAllByCustomerId(Long customerId) {
        return persistenceService.findAllByCustomerId(customerId);

    }

    @Override
    public void delete(Long id) {
        persistenceService.delete(id);
    }
}
