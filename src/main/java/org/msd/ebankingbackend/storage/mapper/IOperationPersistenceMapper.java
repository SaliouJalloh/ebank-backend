package org.msd.ebankingbackend.storage.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.storage.model.Operation;
import org.msd.ebankingbackend.storage.entity.OperationEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IOperationPersistenceMapper extends AbstractEntityMapper<Operation, OperationEntity> {


}
