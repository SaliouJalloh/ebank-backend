package org.msd.ebankingbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Address extends AbstractModel {

    private String street;
    private String city;
    private String zipCode;
    private String country;
    private Customer customer;
}
