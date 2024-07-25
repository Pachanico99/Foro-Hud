package com.challenge.api.domain.answer;

import com.challenge.api.domain.profile.Profile;
import com.challenge.api.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "answers")
@Entity(name = "Answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Profile author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    private Assessment assessment;

    public Answer(String message, Profile author, Topic topic){
        this.message = message;
        this.author = author;
        this.topic = topic;
        this.creationDate = LocalDateTime.now();
        this.assessment = Assessment.UNVALUED;
    }

    public void updateAnswer(String message) {
        this.message = message;
        this.creationDate = LocalDateTime.now();
    }
}
