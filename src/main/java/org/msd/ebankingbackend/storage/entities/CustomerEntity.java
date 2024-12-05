package org.msd.ebankingbackend.storage.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity extends AbstractEntity {

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean active;

    @ManyToOne(cascade = ALL)
    private RoleEntity role;

    @OneToOne
    private AddressEntity address;

    @OneToMany(mappedBy = "customer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AccountEntity> accounts;

    @OneToMany(mappedBy = "customer")
    private List<ContactEntity> contacts;

    @OneToMany(mappedBy = "customer")
    private List<OperationEntity> operations;


}
