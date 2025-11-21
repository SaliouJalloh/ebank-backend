package org.msd.ebankingbackend.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DebitDto(
        @NotNull(message = "L'ID du compte est obligatoire")
        Long accountId,

        @NotNull(message = "Le montant est obligatoire")
        @Min(value = 1, message = "Le montant doit être supérieur à 0")
        BigDecimal amount
) {
}
