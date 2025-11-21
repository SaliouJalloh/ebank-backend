package org.msd.ebankingbackend.infrastructure.mapper;


import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Transaction;
import org.msd.ebankingbackend.infrastructure.persistence.entity.TransactionEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IAccountPersistenceMapper.class})
public interface ITransactionPersistenceMapper {

    @org.mapstruct.Mapping(target = "customer", ignore = true)
    Transaction toModel(TransactionEntity entity);

    @org.mapstruct.Mapping(target = "customer", ignore = true)
    TransactionEntity toEntity(Transaction model);

    List<Transaction> toModels(Iterable<TransactionEntity> entities);

    List<TransactionEntity> toEntities(Iterable<Transaction> models);
}
