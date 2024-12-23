package org.msd.ebankingbackend.domain.service.auth;


import org.msd.ebankingbackend.domain.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
