package org.msd.ebankingbackend.infrastructure.persistence;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Contact;

public interface IContactPersistenceService {
    Contact save(Contact contact);

    void delete(Long id);

    Contact findById(Long id);

    List<Contact> findAll();

    List<Contact> findAllByCustomerId(Long customerId);
}
