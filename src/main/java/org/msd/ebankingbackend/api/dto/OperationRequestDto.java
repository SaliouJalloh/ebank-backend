package org.msd.ebankingbackend.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record OperationRequestDto(
        @NotNull(message = "L'ID du compte est obligatoire")
        Long accountId,

        @NotNull(message = "Le montant est obligatoire")
        @Min(value = 0, message = "Le montant doit être positif")
        Double amount,

        String description
) {
}
