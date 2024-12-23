package org.msd.ebankingbackend.application.dto;

public record TransferRequestDto(Long accountSource,
                                 Long accountDestination,
                                 double amount) {
}
