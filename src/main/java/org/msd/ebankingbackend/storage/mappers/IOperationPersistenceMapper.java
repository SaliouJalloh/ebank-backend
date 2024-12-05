package org.msd.ebankingbackend.storage.mappers;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.storage.entities.OperationEntity;
import org.msd.ebankingbackend.storage.models.Operation;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IOperationPersistenceMapper extends AbstractEntityMapper<Operation, OperationEntity> {


}
