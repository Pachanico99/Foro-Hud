package com.challenge.api.domain.topic;

import com.challenge.api.domain.profile.ProfileRepository;
import com.challenge.api.domain.tag.TagRepository;
import com.challenge.api.domain.tag.Tag;
import com.challenge.api.domain.topic.validations.TopicValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private List<TopicValidator> validator;

    public Topic registerTopic(RegisterTopicDTO registerTopicDTO){
        if(!profileRepository.existsById(registerTopicDTO.profileId())){
            throw new ValidationException("There is no profile with entered id.");
        }

        validator.forEach(v-> v.validate(registerTopicDTO));

        var profile = profileRepository.findById(registerTopicDTO.profileId()).get();

        var tags = registerTopicDTO.tagsIds().stream().map(tagId -> tagRepository.findById(tagId).get()).toList();

        var topic = new Topic(registerTopicDTO.title(), registerTopicDTO.message(), profile, tags);

        return topicRepository.save(topic);
    }

    public ReplyTopicDTO updateTopic(UpdateTopicDTO updateTopicDTO){
        Optional<Topic> optionalTopic = topicRepository.findById(updateTopicDTO.id());

        if (optionalTopic.isEmpty()) {
            throw new ValidationException("Topic not found with id: " + updateTopicDTO.id());
        }

        var registerTopicDTO = new RegisterTopicDTO(updateTopicDTO.title(),
                updateTopicDTO.message(), null ,updateTopicDTO.tagsIds());

        validator.forEach(v-> v.validate(registerTopicDTO));

        var topicInDB = optionalTopic.get();

        List<Tag> tags = List.of();

        if (registerTopicDTO.tagsIds() != null) {
            tags = registerTopicDTO.tagsIds().stream().map(tagId -> {
                var tag = tagRepository.findById(tagId);
                if (tag.isPresent()) {
                    return tag.get();
                }else{
                    throw new ValidationException("Tag not exists.");
                }
            }).toList();
        }

        topicInDB.updateTopic(updateTopicDTO.title(), updateTopicDTO.message(), tags);

        return new ReplyTopicDTO(topicInDB);
    }

    public Page<Topic> findAllTopics(Pageable pagination) {
        return topicRepository.findAllVisibleTopics(pagination);
    }

    public Topic findTopicById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if(topic.isEmpty()){
            throw new ValidationException("No existe el topico con el id: " + id);
        }

        return topic.get();
    }

    public Page<Topic> findByStatus(TOPIC_STATUS status, Pageable pagination){
        return topicRepository.findByStatus(status.toString(), pagination);
    }
}
