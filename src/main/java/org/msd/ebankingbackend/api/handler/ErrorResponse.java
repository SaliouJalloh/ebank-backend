package org.msd.ebankingbackend.api.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int statusCode;
    private final String message;
}