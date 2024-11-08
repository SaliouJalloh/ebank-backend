package org.msd.ebankingbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.msd.ebankingbackend.controller.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IAuthController {

    @Operation(
            description = "Pour s'inscrire à l'application, l'utilisateur doit appeler cette ressource pour avoir le jeton d'accès",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Utilisateur autorisé à se connecter", content = @Content(schema = @Schema(implementation = AuthenticationResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "La syntaxe de la requête est erronée", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Utilisateur non autorisé", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403", description = " accès refusé (interdit)", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Ressource non trouvée", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Erreurs interne du serveur", content = @Content
                    )
            }
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    AuthenticationResponseDto register(@Valid @RequestBody RegisterRequest request, HttpServletResponse response);

    @Operation(
            description = "Pour s'inscrire à l'application, l'utilisateur doit appeler cette ressource pour avoir le jeton d'accès",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Utilisateur autorisé à se connecter", content = @Content(schema = @Schema(implementation = AuthenticationResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "La syntaxe de la requête est erronée", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Utilisateur non autorisé", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403", description = " accès refusé (interdit)", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Ressource non trouvée", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Erreurs interne du serveur", content = @Content
                    )
            }
    )
    @PostMapping("/authenticate")
    AuthenticationResponseDto authenticate(@Valid @RequestBody AuthenticationRequest request, HttpServletResponse response);
}
