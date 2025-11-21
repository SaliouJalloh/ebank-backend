package org.msd.ebankingbackend.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerDto(

        @NotBlank(message = "Le prenom ne doit pas etre vide")
        String firstName,

        @NotBlank(message = "Le nom ne doit pas etre vide")
        String lastName,

        @NotBlank(message = "L'email ne doit pas etre vide")
        @Email(message = "L'email n'est pas conforme")
        String email,

        @NotBlank(message = "Le mot de passe ne doit pas etre vide")
        @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
        String password,

        String iban,
        boolean active) {

}
