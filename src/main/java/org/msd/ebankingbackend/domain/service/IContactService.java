package org.msd.ebankingbackend.domain.service;

import java.util.List;

import org.msd.ebankingbackend.domain.model.Contact;

public interface IContactService {
    Contact save(Contact contactDto);

    List<Contact> findAll();

    Contact findById(Long id);

    List<Contact> findAllByCustomerId(Long customerId);

    void delete(Long id);
}
