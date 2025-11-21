package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.msd.ebankingbackend.infrastructure.enumeration.TransactionType;

import java.math.BigDecimal;

@Builder
public record TransactionRequestDto(
        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Transaction type cannot be null")
        TransactionType type,
        String destinationIban, // This can be null for some transaction types (e.g., deposit)
        @NotNull(message = "Customer ID cannot be null")
        Long customerId
) {
}
