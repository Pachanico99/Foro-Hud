package com.challenge.api.domain.answer;

import java.time.LocalDateTime;

public record ListAnswerDTO(
        String message,
        String authorName,
        String titleTopic,
        LocalDateTime creationDate,
        String assessment
) {
    public ListAnswerDTO(Answer answer) {
        this(answer.getMessage(), answer.getAuthor().getUserName(),
                answer.getTopic().getTitle(), answer.getCreationDate(), answer.getAssessment().toString());
    }
}
