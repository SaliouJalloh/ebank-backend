package org.msd.ebankingbackend.api.dto;

public record ContactDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Long customerId
) {
}
