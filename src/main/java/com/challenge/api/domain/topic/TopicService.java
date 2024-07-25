package com.challenge.api.domain.topic;

import com.challenge.api.domain.profile.ProfileService;
import com.challenge.api.domain.tag.TagRepository;
import com.challenge.api.domain.tag.Tag;
import com.challenge.api.domain.topic.validations.TopicValidator;
import com.challenge.api.infra.errors.PageEmptyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private List<TopicValidator> validator;

    public Topic registerTopic(RegisterTopicDTO registerTopicDTO) throws EntityNotFoundException{
        var profile = profileService.findById(registerTopicDTO.profileId());

        var tags = registerTopicDTO.tagsIds().stream().map(tagId -> tagRepository.findById(tagId).get()).toList();

        validator.forEach(v-> v.validate(registerTopicDTO));

        var topic = new Topic(registerTopicDTO.title(), registerTopicDTO.message(), profile, tags);

        return topicRepository.save(topic);
    }

    public ReplyTopicDTO updateTopic(UpdateTopicDTO updateTopicDTO) throws EntityNotFoundException{
        var topic = findById(updateTopicDTO.id());

        var registerTopicDTO = new RegisterTopicDTO(updateTopicDTO.title(),
                updateTopicDTO.message(), null ,updateTopicDTO.tagsIds());

        validator.forEach(v-> v.validate(registerTopicDTO));

        List<Tag> tags = List.of();

        if (!registerTopicDTO.tagsIds().isEmpty()) {
            tags = registerTopicDTO.tagsIds().stream().map(tagId -> {
                var tag = tagRepository.findById(tagId);
                if (tag.isPresent()) {
                    return tag.get();
                }else{
                    throw new EntityNotFoundException("Tag not exists.");
                }
            }).toList();
        }

        topic.updateTopic(updateTopicDTO.title(), updateTopicDTO.message(), tags);

        return new ReplyTopicDTO(topic);
    }

    public Page<Topic> findAllTopics(Pageable pagination) throws PageEmptyException{
        var topics = topicRepository.findAllVisibleTopics(pagination);

        if(topics.isEmpty()){
            throw new PageEmptyException("There are no topics yet.");
        }

        return topics.get();
    }

    public Topic findById(Long id) throws EntityNotFoundException {
        var topic = topicRepository.findById(id);

        if(topic.isEmpty()){
            throw new EntityNotFoundException("Topic not found with id: " + id);
        }

        return topic.get();
    }

    public Page<Topic> findByStatus(TOPIC_STATUS status, Pageable pagination) throws PageEmptyException{
        var topics = topicRepository.findByStatus(status.toString(), pagination);

        if(topics.isEmpty()){
            throw new PageEmptyException("There are no topics with " + status.toString() + " status yet.");
        }

        return topics.get();
    }
}
