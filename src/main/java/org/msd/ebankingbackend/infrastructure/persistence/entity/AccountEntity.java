package org.msd.ebankingbackend.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import org.msd.ebankingbackend.infrastructure.enumeration.AccountStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Entity(name = "accounts")
public class AccountEntity extends AbstractEntity {

    private String iban;

    private double balance;

    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;

    private String currency;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(mappedBy = "account")
    private List<OperationEntity> operation;
}
