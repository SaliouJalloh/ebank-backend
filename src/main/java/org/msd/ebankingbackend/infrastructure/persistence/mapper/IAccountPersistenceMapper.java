package org.msd.ebankingbackend.infrastructure.persistence.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.CurrentAccount;
import org.msd.ebankingbackend.domain.model.SavingAccount;
import org.msd.ebankingbackend.infrastructure.persistence.entity.AccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CurrentAccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.SavingAccountEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IAccountPersistenceMapper extends AbstractEntityMapper<Account, AccountEntity> {

    CurrentAccount toModel(CurrentAccountEntity AccountEntity);

    SavingAccount toModel(SavingAccountEntity AccountEntity);

    CurrentAccountEntity toEntity(CurrentAccount Account);

    SavingAccountEntity toEntity(SavingAccount Account);
}
