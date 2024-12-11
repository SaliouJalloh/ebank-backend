package org.msd.ebankingbackend.infrastructure.persistence.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Operation;
import org.msd.ebankingbackend.infrastructure.persistence.entity.OperationEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IOperationPersistenceMapper extends AbstractEntityMapper<Operation, OperationEntity> {


}
