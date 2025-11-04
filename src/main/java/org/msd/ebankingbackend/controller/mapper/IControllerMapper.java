package org.msd.ebankingbackend.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.controller.dto.AccountDto;
import org.msd.ebankingbackend.controller.dto.AuthenticationResponseDto;
import org.msd.ebankingbackend.controller.dto.CustomerDto;
import org.msd.ebankingbackend.storage.model.Account;
import org.msd.ebankingbackend.storage.model.Customer;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);

    CustomerDto toCustomerDto(Customer customer);

    AccountDto toAccountDto(Account account);
}
