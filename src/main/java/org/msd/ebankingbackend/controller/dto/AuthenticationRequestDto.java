package org.msd.ebankingbackend.controller.dto;


public record AuthenticationRequestDto(
        String email,
        String password) {
}
