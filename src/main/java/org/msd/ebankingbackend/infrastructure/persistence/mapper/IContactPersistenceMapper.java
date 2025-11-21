package org.msd.ebankingbackend.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.infrastructure.entity.ContactEntity;


@Mapper(componentModel = "spring", uses = {IAccountPersistenceMapper.class, ICustomerPersistenceMapper.class})
public interface IContactPersistenceMapper extends AbstractEntityMapper<Contact, ContactEntity> {
}
