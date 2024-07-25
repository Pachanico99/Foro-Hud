package com.challenge.api.infra.errors;

import org.springframework.http.HttpStatus;

public record ErrorMessage(
        HttpStatus status,
        String message
) {
}
