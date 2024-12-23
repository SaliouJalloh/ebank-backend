package org.msd.ebankingbackend.application.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.application.dto.AccountDto;
import org.msd.ebankingbackend.application.dto.AuthenticationResponseDto;
import org.msd.ebankingbackend.application.dto.CustomerDto;
import org.msd.ebankingbackend.domain.model.Account;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);

    CustomerDto toCustomerDto(Customer customer);

    AccountDto toAccountDto(Account account);
}
