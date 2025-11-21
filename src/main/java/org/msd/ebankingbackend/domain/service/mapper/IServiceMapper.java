package org.msd.ebankingbackend.domain.service.mapper;

import org.mapstruct.Mapper;
import org.msd.ebankingbackend.api.dto.AddressDto;
import org.msd.ebankingbackend.api.dto.TransactionDto;
import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.domain.model.Transaction;

@Mapper(componentModel = "spring")
public interface IServiceMapper {

    Address toAddress(Address address);

    AddressDto toAddressDto(Address address);

    Transaction toTransaction(TransactionDto dto);

    TransactionDto toTransactionDto(Transaction transaction);

    // ...autres méthodes de mapping existantes...
}
