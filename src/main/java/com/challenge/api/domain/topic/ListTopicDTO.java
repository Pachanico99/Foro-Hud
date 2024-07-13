package com.challenge.api.domain.topic;

import java.time.LocalDateTime;
import com.challenge.api.domain.tag.Tag;
import java.util.List;

public record ListTopicDTO(
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        String authorName,
        List<String> tagsNames) {
    public ListTopicDTO(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus().toString(),
                topic.getAuthor().getUserName(), topic.getTags().stream().map(Tag::getName).toList());
    }
}
