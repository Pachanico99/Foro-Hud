package com.challenge.api.domain.tag;

public record TagDTO(
        String name,
        String description
) {
    public TagDTO(Tag tag){
        this(tag.getName(), tag.getDescription());
    }
}
