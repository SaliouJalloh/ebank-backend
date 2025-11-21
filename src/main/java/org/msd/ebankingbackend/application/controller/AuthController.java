package org.msd.ebankingbackend.application.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.application.dto.response.AuthenticationResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
import org.msd.ebankingbackend.domain.service.auth.IAuthenticationService;
import org.msd.ebankingbackend.domain.service.jwt.JwtService;
import org.msd.ebankingbackend.domain.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.msd.ebankingbackend.application.dto.request.AuthenticationRequestDto;
import org.msd.ebankingbackend.application.dto.request.RegisterRequestDto;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API d'authentification et d'enregistrement")
public class AuthController implements IAuthController {

    private final IControllerMapper controllerMapper;
    private final IAuthenticationService authenticationService;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponseDto register(RegisterRequestDto requestDto, HttpServletResponse response) {
        RegisterRequest request = controllerMapper.toRegisterRequest(requestDto);
        AuthenticationResponse authResponse = authenticationService.register(request);
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(authResponse.getAccessToken());

        // Ajoute le cookie à la réponse
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        // Converti et return le DTO
        return controllerMapper.toAuthenticationDto(authResponse);
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto requestDto, HttpServletResponse response) {
        AuthenticationRequest request = controllerMapper.toAuthenticationRequest(requestDto);
        AuthenticationResponse authResponse = authenticationService.authenticate(request);

        ResponseCookie jwtCookie = jwtService.generateJwtCookie(authResponse.getAccessToken());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        return controllerMapper.toAuthenticationDto(authResponse);
    }
}
