package org.msd.ebankingbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SavingAccountDto extends AccountDto {
    private double interestRate;
}
