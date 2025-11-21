package org.msd.ebankingbackend.domain.model;

import org.msd.ebankingbackend.infrastructure.enumeration.RoleName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractModel {

    private RoleName name;
    private Customer customer;
    private String description;
}
