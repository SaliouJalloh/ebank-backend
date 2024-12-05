package org.msd.ebankingbackend.storage.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;
import org.msd.ebankingbackend.storage.models.Customer;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ICustomerPersistenceMapper extends AbstractEntityMapper<Customer, CustomerEntity> {
    CustomerEntity toEntity(RegisterRequest request);
}
