package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequestDto(
        @NotBlank(message = "Le prenom ne doit pas etre vide")
        String firstName,

        @NotBlank(message = "Le nom ne doit pas etre vide")
        String lastName,

        @NotBlank(message = "L'email ne doit pas etre vide")
        @Email(message = "L'email n'est pas conforme")
        String email
) {
}

