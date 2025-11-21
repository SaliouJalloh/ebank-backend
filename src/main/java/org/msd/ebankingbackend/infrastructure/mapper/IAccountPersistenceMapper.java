package org.msd.ebankingbackend.infrastructure.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.CurrentAccount;
import org.msd.ebankingbackend.domain.model.SavingAccount;
import org.msd.ebankingbackend.infrastructure.persistence.entity.AccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.CurrentAccountEntity;
import org.msd.ebankingbackend.infrastructure.persistence.entity.SavingAccountEntity;

@Mapper(componentModel = "spring", uses = {ICustomerPersistenceMapper.class, IAddressPersistenceMapper.class})
public interface IAccountPersistenceMapper extends AbstractEntityMapper<Account, AccountEntity> {

    @Override
    @SubclassMapping(source = CurrentAccount.class, target = CurrentAccountEntity.class)
    @SubclassMapping(source = SavingAccount.class, target = SavingAccountEntity.class)
    @org.mapstruct.Mapping(target = "operation", ignore = true)
    AccountEntity toEntity(Account model);

    @Override
    @SubclassMapping(source = CurrentAccountEntity.class, target = CurrentAccount.class)
    @SubclassMapping(source = SavingAccountEntity.class, target = SavingAccount.class)
    @org.mapstruct.Mapping(target = "operations", ignore = true)
    Account toModel(AccountEntity entity);

    @org.mapstruct.Mapping(target = "operations", ignore = true)
    CurrentAccount toModel(CurrentAccountEntity AccountEntity);

    @org.mapstruct.Mapping(target = "operations", ignore = true)
    SavingAccount toModel(SavingAccountEntity AccountEntity);

    @org.mapstruct.Mapping(target = "operation", ignore = true)
    CurrentAccountEntity toEntity(CurrentAccount Account);

    @org.mapstruct.Mapping(target = "operation", ignore = true)
    SavingAccountEntity toEntity(SavingAccount Account);
}
