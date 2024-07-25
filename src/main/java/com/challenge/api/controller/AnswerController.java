package com.challenge.api.controller;

import com.challenge.api.domain.answer.*;
import com.challenge.api.infra.errors.PageEmptyException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReplyAnswerDTO> registerAnswer(@RequestBody @Valid RegisterAnswerDTO registerAnswerDTO
            , UriComponentsBuilder uriComponentsBuilder) throws EntityNotFoundException {
        var answer = answerService.registerAnswer(registerAnswerDTO);

        ReplyAnswerDTO replyAnswerDTO = new ReplyAnswerDTO(answer);

        URI url = uriComponentsBuilder.path("/answers/{id}").buildAndExpand(answer.getId()).toUri();

        return ResponseEntity.created(url).body(replyAnswerDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListAnswerDTO>> listTopics(@PageableDefault(size = 5) Pageable pagination) throws PageEmptyException {
        return ResponseEntity.ok(answerService.findAllAnswers(pagination).map(ListAnswerDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyAnswerDTO> getTopic(@PathVariable Long id) throws EntityNotFoundException{
        var answer = answerService.findById(id);

        var replyAnswerDTO = new ReplyAnswerDTO(answer);

        return ResponseEntity.ok(replyAnswerDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ReplyAnswerDTO> updateTopic(@RequestBody @Valid UpdateAnswerDTO updateAnswerDTO) throws EntityNotFoundException {
        return ResponseEntity.ok(answerService.updateAnswer(updateAnswerDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        var answer  = answerService.findById(id);

        answerService.deleteAnswer(answer);

        return ResponseEntity.noContent().build();
    }
}
