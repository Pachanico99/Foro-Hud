package com.challenge.api.domain.topic.validations;

import com.challenge.api.domain.topic.RegisterTopicDTO;
import jakarta.validation.ValidationException;

public interface TopicValidator {
    public void validate(RegisterTopicDTO registerTopicDTO) throws ValidationException;
}
