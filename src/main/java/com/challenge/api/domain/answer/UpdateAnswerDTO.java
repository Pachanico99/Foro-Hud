package com.challenge.api.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAnswerDTO(
        @NotNull
        Long id,
        @NotBlank
        String message
) {
}
