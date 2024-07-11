package com.challenge.api.model.topic;

import com.challenge.api.model.tag.TagDTO;

import java.util.List;

public record ReplyTopicDTO(
        String title,
        String message,
        String AuthorName,
        List<TagDTO> tags
) {
    public ReplyTopicDTO(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getAuthor().getUserName()
                , topic.getTags().stream().map(TagDTO::new).toList());
    }
}
