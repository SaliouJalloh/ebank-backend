package org.msd.ebankingbackend.controller.dto;

public record DebitDto(Long accountId,
                       double amount) {
}
