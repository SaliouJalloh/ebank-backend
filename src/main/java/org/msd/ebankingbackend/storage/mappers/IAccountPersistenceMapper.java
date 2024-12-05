package org.msd.ebankingbackend.storage.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.storage.entities.AccountEntity;
import org.msd.ebankingbackend.storage.entities.CurrentAccountEntity;
import org.msd.ebankingbackend.storage.entities.SavingAccountEntity;
import org.msd.ebankingbackend.storage.models.Account;
import org.msd.ebankingbackend.storage.models.CurrentAccount;
import org.msd.ebankingbackend.storage.models.SavingAccount;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IAccountPersistenceMapper extends AbstractEntityMapper<Account, AccountEntity> {

    CurrentAccount toModel(CurrentAccountEntity AccountEntity);

    SavingAccount toModel(SavingAccountEntity AccountEntity);

    CurrentAccountEntity toEntity(CurrentAccount Account);

    SavingAccountEntity toEntity(SavingAccount Account);
}
