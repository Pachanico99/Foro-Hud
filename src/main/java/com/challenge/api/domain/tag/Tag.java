package com.challenge.api.domain.tag;

import com.challenge.api.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tags")
@Entity(name = "Tag")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "tag_name")
    private String name;

    @Column(nullable = false, name = "tag_description")
    private String description;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Topic> topics;
}