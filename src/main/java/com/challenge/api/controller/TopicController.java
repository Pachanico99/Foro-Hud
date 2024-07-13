package com.challenge.api.controller;

import com.challenge.api.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReplyTopicDTO> registerTopic(@RequestBody @Valid RegisterTopicDTO registerTopicDTO,
                                                       UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicService.registerTopic(registerTopicDTO);

        ReplyTopicDTO replyTopicDTO = new ReplyTopicDTO(topic);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(url).body(replyTopicDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListTopicDTO>> listTopics(@PageableDefault(size = 2) Pageable pagination){
        return ResponseEntity.ok(topicService.findAllTopics(pagination).map(ListTopicDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyTopicDTO> getTopic(@PathVariable Long id) {
        Topic topic = topicService.findTopicById(id);

        var replyTopicDTO = new ReplyTopicDTO(topic);

        return ResponseEntity.ok(replyTopicDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ReplyTopicDTO> updateTopic(@RequestBody @Valid UpdateTopicDTO updateTopicDTO) {
        return ResponseEntity.ok(topicService.updateTopic(updateTopicDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Topic topic  = topicService.findTopicById(id);

        topic.deleteTopic();

        return ResponseEntity.noContent().build();
    }
}
