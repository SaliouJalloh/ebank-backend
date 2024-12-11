package org.msd.ebankingbackend.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class EntityValidationException extends RuntimeException {

    private final Set<String> violations;

    private final String violationSource;

}
