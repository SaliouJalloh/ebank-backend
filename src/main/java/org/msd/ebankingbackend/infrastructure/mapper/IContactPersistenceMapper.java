package org.msd.ebankingbackend.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.infrastructure.persistence.entity.ContactEntity;


@Mapper(componentModel = "spring", uses = {IAccountPersistenceMapper.class, ICustomerPersistenceMapper.class})
public interface IContactPersistenceMapper extends AbstractEntityMapper<Contact, ContactEntity> {
}
