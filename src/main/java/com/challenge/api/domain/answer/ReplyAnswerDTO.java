package com.challenge.api.domain.answer;

import java.time.LocalDateTime;

public record ReplyAnswerDTO(
        Long id,
        String message,
        String authorName,
        String titleTopic,
        LocalDateTime creationDate,
        String assessment
) {
    public ReplyAnswerDTO(Answer answer) {
        this(answer.getId(), answer.getMessage(), answer.getAuthor().getUserName(),
                answer.getTopic().getTitle(), answer.getCreationDate(), answer.getAssessment().toString());
    }
}
