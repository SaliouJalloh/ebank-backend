package org.msd.ebankingbackend.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.controller.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.controller.mappers.IControllerMapper;
import org.msd.ebankingbackend.service.auth.IAuthenticationService;
import org.msd.ebankingbackend.service.jwt.JwtService;
import org.msd.ebankingbackend.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements IAuthController {

    private final IControllerMapper controllerMapper;
    private final IAuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationResponseDto register(RegisterRequest request, HttpServletResponse response) {

        AuthenticationResponse authResponse = authenticationService.register(request);
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(authResponse.getAccessToken());

        // Ajoute le cookie à la réponse
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        // Converti et retourne le DTO
        return controllerMapper.toAuthenticationDto(authResponse);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequest request, HttpServletResponse response) {

        AuthenticationResponse authResponse = authenticationService.authenticate(request);

        ResponseCookie jwtCookie = jwtService.generateJwtCookie(authResponse.getAccessToken());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        return controllerMapper.toAuthenticationDto(authResponse);
    }
}
