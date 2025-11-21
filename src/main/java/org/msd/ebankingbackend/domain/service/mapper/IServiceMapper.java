package org.msd.ebankingbackend.domain.service.mapper;

import org.mapstruct.Mapper;
import org.msd.ebankingbackend.application.dto.response.AddressResponseDto;
import org.msd.ebankingbackend.application.dto.response.TransactionResponseDto;
import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.domain.model.Transaction;

@Mapper(componentModel = "spring")
public interface IServiceMapper {

    Address toAddress(Address address);

    AddressResponseDto toAddressResponseDto(Address address);

    Transaction toTransaction(TransactionResponseDto dto);

    TransactionResponseDto toTransactionResponseDto(Transaction transaction);

    // ...autres méthodes de mapping existantes...
}
