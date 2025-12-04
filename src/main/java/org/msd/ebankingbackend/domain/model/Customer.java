package org.msd.ebankingbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AbstractModel {

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    private boolean active;

    private Address address;
    private Role role;

    private List<Account> accounts;

    private List<Contact> contacts;

    private List<Operation> operations;
}
