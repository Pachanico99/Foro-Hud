package com.challenge.api.model.topic.validations;

import com.challenge.api.model.topic.RegisterTopicDTO;

import com.challenge.api.model.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnrepeatedTitle implements TopicValidator{

    @Autowired
    private TopicRepository topicRepository;

    public void validate(RegisterTopicDTO registerTopicDTO)  {
        var unrepeatedTitle = topicRepository.findTopicByTitle(registerTopicDTO.title());

        if(unrepeatedTitle.isEmpty()){
            throw new ValidationException("It is not allowed to enter titles, which already exist.");
        }
    }
}
