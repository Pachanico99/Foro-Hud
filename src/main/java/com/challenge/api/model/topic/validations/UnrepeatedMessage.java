package com.challenge.api.model.topic.validations;

import com.challenge.api.model.topic.RegisterTopicDTO;
import com.challenge.api.model.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnrepeatedMessage implements TopicValidator{

    @Autowired
    private TopicRepository topicRepository;

    public void validate(RegisterTopicDTO registerTopicDTO)  {
        var unrepeatedTitle = topicRepository.findTopicByMessage(registerTopicDTO.message());

        if(unrepeatedTitle.isEmpty()){
            throw new ValidationException("It is not allowed to enter titles, which already exist.");
        }
    }
}
