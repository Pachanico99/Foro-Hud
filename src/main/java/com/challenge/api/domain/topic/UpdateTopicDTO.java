package com.challenge.api.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateTopicDTO(
        @NotNull
        Long id,
        String title,
        String message,
        List<Long> tagsIds
) {
}
