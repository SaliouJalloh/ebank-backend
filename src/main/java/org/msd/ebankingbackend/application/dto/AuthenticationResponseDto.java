package org.msd.ebankingbackend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponseDto(

        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("token_type")
        String tokenType
) {
}
