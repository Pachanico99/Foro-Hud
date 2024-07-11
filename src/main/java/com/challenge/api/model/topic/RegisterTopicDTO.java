package com.challenge.api.model.topic;

import java.util.List;

public record RegisterTopicDTO(
        String title,
        String message,
        Long profileId,
        List<Long> tagsIds
) {
}
