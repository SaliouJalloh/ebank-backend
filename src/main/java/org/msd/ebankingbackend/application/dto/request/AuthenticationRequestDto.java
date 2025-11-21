package org.msd.ebankingbackend.application.dto.request;


public record AuthenticationRequestDto(
        String email,
        String password) {
}
