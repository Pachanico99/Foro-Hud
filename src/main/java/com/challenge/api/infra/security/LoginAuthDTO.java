package com.challenge.api.infra.security;

import jakarta.validation.constraints.NotBlank;

public record LoginAuthDTO(
        @NotBlank
        String userName,
        @NotBlank
        String password
) {
}