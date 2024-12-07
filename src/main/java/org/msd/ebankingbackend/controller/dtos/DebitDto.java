package org.msd.ebankingbackend.controller.dtos;

public record DebitDto(Long accountId,
                       double amount) {
}
