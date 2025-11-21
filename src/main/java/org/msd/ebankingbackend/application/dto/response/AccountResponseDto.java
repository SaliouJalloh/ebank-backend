package org.msd.ebankingbackend.application.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountResponseDto(
        Long id,

        @NotNull(message = "Le solde est obligatoire")
        @Min(value = 0, message = "Le solde doit être positif ou nul")
        BigDecimal balance,

        @NotNull(message = "L'ID du client est obligatoire")
        Integer customerId,

        @NotNull(message = "Le type de compte est obligatoire")
        String type
) {
}
