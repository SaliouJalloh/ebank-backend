package org.msd.ebankingbackend.api.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.api.dto.AccountDto;
import org.msd.ebankingbackend.api.dto.AddressDto;
import org.msd.ebankingbackend.api.dto.AuthenticationResponseDto;
import org.msd.ebankingbackend.api.dto.ContactDto;
import org.msd.ebankingbackend.api.dto.CreateAccountDto;
import org.msd.ebankingbackend.api.dto.CustomerDto;
import org.msd.ebankingbackend.api.dto.TransactionDto;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.Address;
import org.msd.ebankingbackend.domain.model.Contact;
import org.msd.ebankingbackend.domain.model.CurrentAccount;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.model.SavingAccount;
import org.msd.ebankingbackend.domain.model.Transaction;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);

    CustomerDto toCustomerDto(Customer customer);

    AccountDto toAccountDto(Account account);

    AddressDto toAddressDto(Address address);

    TransactionDto toTransactionDto(Transaction transaction);

    ContactDto toContactDto(Contact savedContact);

    // Mappings for Request DTOs
    default Account toAccount(CreateAccountDto dto) {
        if (dto == null) {
            return null;
        }
        // Basic mapping logic - ideally this should be handled by MapStruct with specific methods
        // but since we have inheritance (Current/Saving), manual or specialized mapping is often needed.
        // For simplicity here, we can delegate to specific methods based on type.
        if ("CURRENT".equalsIgnoreCase(dto.type())) {
            return toCurrentAccount(dto);
        } else if ("SAVING".equalsIgnoreCase(dto.type())) {
            return toSavingAccount(dto);
        }
        throw new IllegalArgumentException("Unknown account type: " + dto.type());
    }

    @org.mapstruct.Mapping(target = "balance", source = "initialBalance")
    @org.mapstruct.Mapping(target = "overDraft", source = "overDraft")
    @org.mapstruct.Mapping(target = "customer.id", source = "customerId")
    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "status", constant = "CREATED")
    @org.mapstruct.Mapping(target = "operations", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true) // Assuming AbstractModel has these
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    CurrentAccount toCurrentAccount(CreateAccountDto dto);

    @org.mapstruct.Mapping(target = "balance", source = "initialBalance")
    @org.mapstruct.Mapping(target = "interestRate", source = "interestRate")
    @org.mapstruct.Mapping(target = "customer.id", source = "customerId")
    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "status", constant = "CREATED")
    @org.mapstruct.Mapping(target = "operations", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    SavingAccount toSavingAccount(CreateAccountDto dto);
}
