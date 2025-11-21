package org.msd.ebankingbackend.infrastructure.persistence.mapper;


import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Operation;
import org.msd.ebankingbackend.infrastructure.entity.OperationEntity;

@Mapper(componentModel = "spring", uses = {IAccountPersistenceMapper.class})
public interface IOperationPersistenceMapper extends AbstractEntityMapper<Operation, OperationEntity> {


}
