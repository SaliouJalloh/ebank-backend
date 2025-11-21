package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterRequestDto(
        @NotBlank(message = "firstname is required")
        String firstname,
        @NotBlank(message = "lastname is required")
        String lastname,
        @NotBlank(message = "email is required")
        @Email(message = "email format is not valid")
        String email,
        @NotBlank(message = "password is required")
        String password
) {
}
