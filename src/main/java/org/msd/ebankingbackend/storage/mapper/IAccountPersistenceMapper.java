package org.msd.ebankingbackend.storage.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.msd.ebankingbackend.storage.model.Account;
import org.msd.ebankingbackend.storage.model.CurrentAccount;
import org.msd.ebankingbackend.storage.model.SavingAccount;
import org.msd.ebankingbackend.storage.entity.AccountEntity;
import org.msd.ebankingbackend.storage.entity.CurrentAccountEntity;
import org.msd.ebankingbackend.storage.entity.SavingAccountEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IAccountPersistenceMapper extends AbstractEntityMapper<Account, AccountEntity> {

    @Override
    @SubclassMapping(source = CurrentAccount.class, target = CurrentAccountEntity.class)
    @SubclassMapping(source = SavingAccount.class, target = SavingAccountEntity.class)
    AccountEntity toEntity(Account model);

    @Override
    @SubclassMapping(source = CurrentAccountEntity.class, target = CurrentAccount.class)
    @SubclassMapping(source = SavingAccountEntity.class, target = SavingAccount.class)
    Account toModel(AccountEntity entity);

    CurrentAccount toModel(CurrentAccountEntity AccountEntity);

    SavingAccount toModel(SavingAccountEntity AccountEntity);

    CurrentAccountEntity toEntity(CurrentAccount Account);

    SavingAccountEntity toEntity(SavingAccount Account);
}
