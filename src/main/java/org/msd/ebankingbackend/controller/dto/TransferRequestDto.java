package org.msd.ebankingbackend.controller.dto;

public record TransferRequestDto(Long accountSource,
                                 Long accountDestination,
                                 double amount) {
}
