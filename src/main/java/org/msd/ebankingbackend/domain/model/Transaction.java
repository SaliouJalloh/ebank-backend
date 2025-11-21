package org.msd.ebankingbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.msd.ebankingbackend.infrastructure.enumeration.TransactionType;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends AbstractModel {
    private BigDecimal amount;
    private TransactionType type;
    private String destinationIban;
    private LocalDateTime dateTime;
    private Customer customer;
}
