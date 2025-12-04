package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDto(
        @NotBlank(message = "Le prenom ne doit pas etre vide")
        String firstname,

        @NotBlank(message = "Le nom ne doit pas etre vide")
        String lastname,

        @NotBlank(message = "L'email ne doit pas etre vide")
        @Email(message = "L'email n'est pas conforme")
        String email

        /*@NotBlank(message = "Le mot de passe ne doit pas etre vide")
        @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
        String password*/
) {
}


