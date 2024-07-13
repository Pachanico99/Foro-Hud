package com.challenge.api.domain.topic;

import com.challenge.api.domain.tag.TagDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ReplyTopicDTO(
        String title,
        String message,
        String AuthorName,
        LocalDateTime creationDate,
        List<TagDTO> tags
) {
    public ReplyTopicDTO(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getAuthor().getUserName(), topic.getCreationDate()
                , topic.getTags().stream().map(TagDTO::new).toList());
    }
}