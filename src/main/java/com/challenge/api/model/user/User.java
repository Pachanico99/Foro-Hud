package com.challenge.api.model.user;

import com.challenge.api.model.answer.Answer;
import com.challenge.api.model.topic.Topic;
import com.challenge.api.model.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    @OneToMany
    private List<Topic> topics;
    @OneToMany
    private List<Answer> answers;
    @OneToMany
    private List<Vote> votes;

}
