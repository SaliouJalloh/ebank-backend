package org.msd.ebankingbackend.application.dto.response;

public record ContactResponseDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Long customerId
) {
}
