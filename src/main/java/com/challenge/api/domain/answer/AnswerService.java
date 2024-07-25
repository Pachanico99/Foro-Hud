package com.challenge.api.domain.answer;

import com.challenge.api.domain.profile.ProfileService;
import com.challenge.api.domain.topic.*;
import com.challenge.api.infra.errors.PageEmptyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TopicService topicService;

    public Answer registerAnswer(RegisterAnswerDTO registerAnswerDTO) throws EntityNotFoundException {
        var author = profileService.findById(registerAnswerDTO.profileId());

        var topic = topicService.findById(registerAnswerDTO.topicId());

        var answer = new Answer(registerAnswerDTO.message(), author, topic);

        return  answerRepository.save(answer);
    }

    public Page<Answer> findAllAnswers(Pageable pagination) throws PageEmptyException {
        var answers = answerRepository.findAllVisibleAnswers(pagination);

        if(answers.isEmpty()){
            throw new PageEmptyException("There are no answers yet.");
        }

        return answers.get();
    }

    public Answer findById(Long id) throws EntityNotFoundException {
        var answer = answerRepository.findById(id);

        if(answer.isEmpty()){
            throw new EntityNotFoundException("Answer not found with id: " + id);
        }

        return answer.get();
    }

    public ReplyAnswerDTO updateAnswer(UpdateAnswerDTO updateAnswerDTO) throws EntityNotFoundException {
        var answer = findById(updateAnswerDTO.id());

        answer.updateAnswer(updateAnswerDTO.message());

        return new ReplyAnswerDTO(answer);
    }

    public void deleteAnswer(Answer answer) {
        answerRepository.deleteById(answer.getId());
    }
}
