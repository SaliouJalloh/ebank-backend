package org.msd.ebankingbackend.infrastructure.persistence.service;

import org.msd.ebankingbackend.domain.model.Contact;

import java.util.List;

public interface IContactPersistenceService {
    Contact save(Contact contact);

    void delete(Long id);

    Contact findById(Long id);

    List<Contact> findAll();

    List<Contact> findAllByCustomerId(Long customerId);
}
