package org.msd.ebankingbackend.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressResponseDto(
        Integer id,

        @NotBlank(message = "La rue est obligatoire")
        @Size(min = 3, max = 100, message = "La rue doit contenir entre 3 et 100 caractères")
        String street,

        @NotBlank(message = "La ville est obligatoire")
        @Size(min = 2, max = 50, message = "La ville doit contenir entre 2 et 50 caractères")
        String city,

        @NotBlank(message = "Le code postal est obligatoire")
        @Size(min = 5, max = 10, message = "Le code postal doit contenir entre 5 et 10 caractères")
        String zipCode,

        @NotBlank(message = "Le pays est obligatoire")
        @Size(min = 2, max = 50, message = "Le pays doit contenir entre 2 et 50 caractères")
        String country
) {
}
