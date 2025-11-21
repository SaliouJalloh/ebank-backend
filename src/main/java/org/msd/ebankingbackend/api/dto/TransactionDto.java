package org.msd.ebankingbackend.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.msd.ebankingbackend.infrastructure.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        Integer id,

        @NotNull(message = "Le montant est obligatoire")
        @Min(value = 1, message = "Le montant doit être supérieur à 0")
        BigDecimal amount,

        @NotNull(message = "Le type de transaction est obligatoire")
        TransactionType type,

        String description,

        LocalDateTime dateTime,

        @NotNull(message = "L'ID utilisateur est obligatoire")
        Integer userId
) {
}
