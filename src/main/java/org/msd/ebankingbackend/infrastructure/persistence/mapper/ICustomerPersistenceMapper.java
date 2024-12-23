package org.msd.ebankingbackend.infrastructure.persistence.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CustomerEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ICustomerPersistenceMapper extends AbstractEntityMapper<Customer, CustomerEntity> {
    CustomerEntity toEntity(RegisterRequest request);
}
