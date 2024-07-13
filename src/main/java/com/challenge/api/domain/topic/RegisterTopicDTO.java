package com.challenge.api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterTopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long profileId,
        @NotEmpty
        List<Long> tagsIds
) {
}
