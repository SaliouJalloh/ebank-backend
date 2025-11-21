package org.msd.ebankingbackend.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.infrastructure.persistence.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface IAddressPersistenceMapper extends AbstractEntityMapper<Address, AddressEntity> {

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "county", ignore = true)
    @Mapping(target = "houseNumber", ignore = true)
    AddressEntity toEntity(Address model);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "country", ignore = true)
    Address toModel(AddressEntity entity);
}
