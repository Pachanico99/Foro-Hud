package com.challenge.api.model.topic.validations;

import com.challenge.api.model.topic.RegisterTopicDTO;

public interface TopicValidator {
    public void validate(RegisterTopicDTO registerTopicDTO);
}
