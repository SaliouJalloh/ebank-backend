package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ContactRequestDto(
        @NotBlank(message = "Firstname cannot be blank")
        String firstname,
        @NotBlank(message = "Lastname cannot be blank")
        String lastname,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email format is not valid")
        String email,
        @NotBlank(message = "IBAN cannot be blank")
        String iban,
        @NotNull(message = "Customer ID cannot be null")
        Long customerId
) {
}
