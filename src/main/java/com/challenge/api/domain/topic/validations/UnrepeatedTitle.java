package com.challenge.api.domain.topic.validations;

import com.challenge.api.domain.topic.RegisterTopicDTO;
import com.challenge.api.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnrepeatedTitle implements TopicValidator{
    @Autowired
    private TopicRepository topicRepository;

    public void validate(RegisterTopicDTO registerTopicDTO) throws ValidationException {
        var unrepeatedTitle = topicRepository.findTopicByTitle(registerTopicDTO.title());

        if(unrepeatedTitle.isPresent()){
            throw new ValidationException("It is not allowed to enter titles, which already exist.");
        }
    }
}