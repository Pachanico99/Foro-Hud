package com.challenge.api.model.topic;

import com.challenge.api.model.answer.Answer;
import com.challenge.api.model.tag.Tag;
import com.challenge.api.model.vote.Vote;
import com.challenge.api.model.profile.Profile;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Profile author;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vote> votes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topic_X_tag",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @Enumerated(EnumType.STRING)
    private TOPIC_STATUS status;

    private int views;

    public Topic(String title, String message, Profile profile, List<Tag> tags) {
        this.title = title;
        this.message = message;
        this.creationDate = LocalDateTime.now();
        this.author = profile;
        this.tags = tags;
        this.status = TOPIC_STATUS.OPEN;
        this.views = 0;
    }

    public void updateTopic(String title, String message, List<Tag> tags) {
        boolean updated = false;

        if (title != null) {
            this.title = title;
            updated = true;
        }
        if (message != null) {
            this.message = message;
            updated = true;
        }
        if(!tags.isEmpty()){
            this.tags = tags;
            updated = true;
        }
        if(updated){
            this.creationDate = LocalDateTime.now();
        }
    }

    public void deleteTopic() {
        this.status = TOPIC_STATUS.DELETE;
    }
}
