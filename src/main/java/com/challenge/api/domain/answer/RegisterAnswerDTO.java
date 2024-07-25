package com.challenge.api.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterAnswerDTO(
        @NotBlank
        String message,
        @NotNull
        Long profileId,
        @NotNull
        Long topicId
) {
}
