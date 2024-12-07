package org.msd.ebankingbackend.controller.dtos;

public record TransferRequestDto(Long accountSource,
                                 Long accountDestination,
                                 double amount) {
}
