package org.msd.ebankingbackend.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountDto(
        @NotNull(message = "L'ID du client est obligatoire")
        Long customerId,

        @NotNull(message = "Le type de compte est obligatoire (CURRENT ou SAVING)")
        String type,

        @Min(value = 0, message = "Le solde initial doit être positif ou nul")
        BigDecimal initialBalance,

        String currency,

        // Champs spécifiques
        Double overDraft, // Pour CurrentAccount
        Double interestRate // Pour SavingAccount
) {
}
