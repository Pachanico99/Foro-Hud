package com.challenge.api.model.topic;

import java.util.List;

public record UpdateTopicDTO(
        Long id,
        String title,
        String message,
        List<Long> tagsIds
) {
}
