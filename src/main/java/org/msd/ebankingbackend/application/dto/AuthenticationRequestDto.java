package org.msd.ebankingbackend.application.dto;


public record AuthenticationRequestDto(
        String email,
        String password) {
}
