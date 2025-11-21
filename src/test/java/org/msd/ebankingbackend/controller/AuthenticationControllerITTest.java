package org.msd.ebankingbackend.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.msd.ebankingbackend.EbankingBackendApplication;
import org.msd.ebankingbackend.application.dto.response.AuthenticationResponseDto;
import org.msd.ebankingbackend.application.mapper.IControllerMapper;
import org.msd.ebankingbackend.config.TestMapperConfiguration;
import org.msd.ebankingbackend.domain.service.auth.AuthenticationService;
import org.msd.ebankingbackend.domain.service.jwt.JwtService;
import org.msd.ebankingbackend.application.dto.request.RegisterRequestDto;
import org.msd.ebankingbackend.domain.service.payload.request.RegisterRequest;
import static org.msd.ebankingbackend.tools.DataProviderTest.buildRegisterRequestDto;
import static org.msd.ebankingbackend.tools.DataProviderTest.buildRegisterRequest;
import org.msd.ebankingbackend.domain.service.payload.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.msd.ebankingbackend.tools.DataProviderTest.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc // Enables MockMvc for testing the controller
@SpringBootTest(classes = EbankingBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
@Import(TestMapperConfiguration.class)
public class AuthenticationControllerITTest {
    public static final String baseUrl = "/api/v1/auth/";
    private final String REGISTER_PATH = baseUrl + "register";
    private final String AUTHENTICATE_PATH = baseUrl + "authenticate";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private IControllerMapper controllerMapper;

    private ObjectMapper objectMapper;

    private RegisterRequestDto registerRequestDto;
    private AuthenticationResponseDto authenticationResponseDto;
    private AuthenticationResponse authenticationResponse;


    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        registerRequestDto = buildRegisterRequestDto();
        authenticationResponse = buildAuthenticationResponse();
        authenticationResponseDto = buildAuthenticationResponseDto();
    }

    @Test
    void register_shouldReturnDtoAndSetJwtCookie() throws Exception {
        // Given
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "testAccessToken")
                .httpOnly(true).secure(false).path("/")
                .maxAge(3600).build();

        when(controllerMapper.toRegisterRequest(any(RegisterRequestDto.class))).thenReturn(buildRegisterRequest());
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(authenticationResponse);
        when(jwtService.generateJwtCookie(eq("testAccessToken"))).thenReturn(jwtCookie);
        when(controllerMapper.toAuthenticationDto(any(AuthenticationResponse.class))).thenReturn(authenticationResponseDto);

        // when
        MvcResult result = mockMvc.perform(post(REGISTER_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequestDto)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(authenticationResponseDto)))
                .andReturn();

        // Then
        HttpServletResponse servletResponse = result.getResponse();
        String cookieHeader = servletResponse.getHeader(HttpHeaders.SET_COOKIE);
        assert cookieHeader != null;
        assertTrue(cookieHeader.contains("jwt=testAccessToken"));
    }

   /* @Test
    void testRegister_KO() throws Exception {
        when(authenticationService.register(any(RegisterRequest.class)))
                .thenThrow(new LoginGenerationException("registration failed"));

        mockMvc.perform(post(REGISTER_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().is4xxClientError());
    }*/

}

