package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequestDto(
        @NotNull(message = "L'ID du compte source est obligatoire")
        Long sourceAccountId,

        @NotNull(message = "L'ID du compte destination est obligatoire")
        Long destinationAccountId,

        @NotNull(message = "Le montant est obligatoire")
        @Min(value = 1, message = "Le montant doit être supérieur à 0")
        BigDecimal amount
) {
}
