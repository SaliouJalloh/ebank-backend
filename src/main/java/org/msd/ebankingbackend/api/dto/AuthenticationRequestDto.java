package org.msd.ebankingbackend.api.dto;


public record AuthenticationRequestDto(
        String email,
        String password) {
}
