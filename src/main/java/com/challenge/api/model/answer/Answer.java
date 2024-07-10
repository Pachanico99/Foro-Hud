package com.challenge.api.model.answer;

import com.challenge.api.model.topic.Topic;
import com.challenge.api.model.user.User;
import com.challenge.api.model.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToOne
    private User user;
    @ManyToOne
    private Topic topic;
    @OneToMany
    private List<Vote> votes;
    private LocalDateTime creationDate;
    private Boolean accepted;
}
