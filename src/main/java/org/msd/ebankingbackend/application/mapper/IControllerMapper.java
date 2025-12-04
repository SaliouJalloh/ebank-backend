package org.msd.ebankingbackend.application.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.application.dto.request.*;
import org.msd.ebankingbackend.application.dto.response.*;
import org.msd.ebankingbackend.domain.model.*;
import org.msd.ebankingbackend.domain.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);

    CustomerResponseDto toCustomerResponseDto(Customer customer);

    AccountResponseDto toAccountResponseDto(Account account);

    AddressResponseDto toAddressResponseDto(Address address);

    TransactionResponseDto toTransactionResponseDto(Transaction transaction);

    ContactResponseDto toContactResponseDto(Contact savedContact);

    // Mappings for Request DTOs - Customer
    @org.mapstruct.Mapping(target = "id", ignore = true)
//    @org.mapstruct.Mapping(target = "password", source = "password")
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    @org.mapstruct.Mapping(target = "active", constant = "true")
    Customer toCustomer(CustomerRequestDto dto);

    @org.mapstruct.Mapping(target = "id", ignore = true)
//    @org.mapstruct.Mapping(target = "password", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    @org.mapstruct.Mapping(target = "active", ignore = true)
    Customer toCustomer(UpdateCustomerRequestDto dto);

    // Mappings for Request DTOs - Account
    default Account toAccount(AccountRequestDto dto) {
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
    CurrentAccount toCurrentAccount(AccountRequestDto dto);

    @org.mapstruct.Mapping(target = "balance", source = "initialBalance")
    @org.mapstruct.Mapping(target = "interestRate", source = "interestRate")
    @org.mapstruct.Mapping(target = "customer.id", source = "customerId")
    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "status", constant = "CREATED")
    @org.mapstruct.Mapping(target = "operations", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    SavingAccount toSavingAccount(AccountRequestDto dto);

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "customer.id", source = "customerId")
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    Address toAddress(AddressRequestDto dto);

    // Mappings for Request DTOs - Auth
    RegisterRequest toRegisterRequest(RegisterRequestDto dto);

    AuthenticationRequest toAuthenticationRequest(AuthenticationRequestDto dto);

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "customer.id", source = "customerId")
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    Contact toContact(ContactRequestDto dto);

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "customer.id", source = "customerId")
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    @org.mapstruct.Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
    Transaction toTransaction(TransactionRequestDto dto);
}
