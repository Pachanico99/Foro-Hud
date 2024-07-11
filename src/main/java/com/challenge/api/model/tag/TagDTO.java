package com.challenge.api.model.tag;

public record TagDTO(
        String name,
        String description
) {
    public TagDTO(Tag tag){
        this(tag.getName(), tag.getDescription());
    }
}
