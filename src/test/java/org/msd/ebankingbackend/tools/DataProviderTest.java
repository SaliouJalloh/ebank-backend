package org.msd.ebankingbackend.tools;

import org.msd.ebankingbackend.application.dto.request.RegisterRequestDto;
import org.msd.ebankingbackend.application.dto.response.AuthenticationResponseDto;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;
import org.msd.ebankingbackend.infrastructure.entity.CustomerEntity;
import org.msd.ebankingbackend.infrastructure.entity.RoleEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.msd.ebankingbackend.infrastructure.enumeration.RoleName.ROLE_USER;


@ActiveProfiles("test")
public class DataProviderTest {

    public static CustomerEntity buildCustomerEntity() {
        return CustomerEntity.builder()
                .firstName("test")
                .lastName("Ba")
                .email("test@gmail.com")
                .password("test@224")
                .active(true)
                .role(createRoles())
                .build();
    }

    public static Customer buildCustomer() {
        return Customer.builder()
                .firstName("test")
                .lastName("Ba")
                .email("test@gmail.com")
                .password("test@224")
                .build();
    }

    public static RoleEntity createRoles() {
        return RoleEntity.builder()
                .name(ROLE_USER)
                .description("User who utilizes transportation services provided by drivers")
                .build();
    }

    public static RegisterRequest buildRegisterRequest() {
        return RegisterRequest.builder()
                .firstname("Bala")
                .lastname("Diallo")
                .email("john.doe@example.com")
                .password("Password&123")
                .build();
    }

    public static org.msd.ebankingbackend.application.dto.request.RegisterRequestDto buildRegisterRequestDto() {
        return org.msd.ebankingbackend.application.dto.request.RegisterRequestDto.builder()
                .firstname("Bala")
                .lastname("Diallo")
                .email("john.doe@example.com")
                .password("Password&123")
                .build();
    }

    public static AuthenticationResponse buildAuthenticationResponse() {
        return AuthenticationResponse.builder()
                .accessToken("testAccessToken")
                .tokenType("Bearer")
                .build();
    }

    public static AuthenticationResponseDto buildAuthenticationResponseDto() {
        return AuthenticationResponseDto.builder()
                .accessToken("testAccessToken")
                .tokenType("Bearer")
                .build();
    }
}
