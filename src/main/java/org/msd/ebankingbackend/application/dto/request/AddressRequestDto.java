package org.msd.ebankingbackend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressRequestDto(
        @NotBlank(message = "Street cannot be blank")
        String street,
        @NotBlank(message = "City cannot be blank")
        String city,
        @NotBlank(message = "Zip code cannot be blank")
        String zipCode,
        @NotBlank(message = "Country cannot be blank")
        String country,
        @NotNull(message = "Customer ID cannot be null")
        Long customerId
) {
}
