package com.challenge.api.model.topic;

import com.challenge.api.model.answer.Answer;
import com.challenge.api.model.user.User;
import com.challenge.api.model.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    @ManyToOne
    private User author;
    @OneToMany
    private List<Answer> answers;
    @OneToMany
    private List<Vote> votes;
    private STATUS status;
    private int views;
}
