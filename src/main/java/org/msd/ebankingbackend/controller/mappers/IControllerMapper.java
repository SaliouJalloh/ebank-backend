package org.msd.ebankingbackend.controller.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.controller.dtos.AccountDto;
import org.msd.ebankingbackend.controller.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.controller.dtos.CustomerDto;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;
import org.msd.ebankingbackend.storage.models.Account;
import org.msd.ebankingbackend.storage.models.Customer;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);

    CustomerDto toCustomerDto(Customer customer);

    AccountDto toAccountDto(Account account);
}
