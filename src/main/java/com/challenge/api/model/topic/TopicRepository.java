package com.challenge.api.model.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    @Query("""
            SELECT t FROM Topic t WHERE t.title = :title\s
            AND t.status NOT IN ('DELETE', 'CLOSE', 'PROTECTED')
           \s""")
    Optional<Topic> findTopicByTitle(String title);

    @Query("""
            SELECT t FROM Topic t WHERE t.message = :message\s
            AND t.status NOT IN ('DELETE', 'CLOSE', 'PROTECTED')
           \s""")
    Optional<Topic> findTopicByMessage(String message);

    @Query("""
            SELECT t FROM Topic t WHERE t.status NOT IN\s
            ('DELETE', 'CLOSE', 'PROTECTED')
           \s""")
    Page<Topic> findAllVisibleTopics(Pageable pagination);

    Page<Topic> findByStatus(String status, Pageable pagination);
}
