package org.msd.ebankingbackend.storage.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.storage.model.Customer;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.storage.entity.CustomerEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ICustomerPersistenceMapper extends AbstractEntityMapper<Customer, CustomerEntity> {
    CustomerEntity toEntity(RegisterRequest request);
}
