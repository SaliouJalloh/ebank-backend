package org.msd.ebankingbackend.domain.model;


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
public class Account extends AbstractModel {

    private String iban;

    private double balance;

    private AccountStatus status;

    private String currency;

    private Customer customer;

    private List<Operation> operations;
}
