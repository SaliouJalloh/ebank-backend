package org.msd.ebankingbackend.application.dto;

public record DebitDto(Long accountId,
                       double amount) {
}
