package com.challenge.api.domain.topic.validations;

import com.challenge.api.domain.topic.RegisterTopicDTO;

public interface TopicValidator {
    public void validate(RegisterTopicDTO registerTopicDTO);
}
