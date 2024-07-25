package com.challenge.api.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    @Query("""
            SELECT t FROM Topic t WHERE t.title = :title
            AND t.status NOT IN ('DELETE', 'CLOSE', 'PROTECTED')
           """)
    Optional<Topic> findTopicByTitle(String title);

    @Query("""
            SELECT t FROM Topic t WHERE t.message = :message
            AND t.status NOT IN ('DELETE', 'CLOSE', 'PROTECTED')
           """)
    Optional<Topic> findTopicByMessage(String message);

    @Query("""
            SELECT t FROM Topic t 
            WHERE LOWER(t.message) = LOWER(:message)
            AND t.status NOT IN ('DELETE', 'CLOSE', 'PROTECTED')
       """)
    Optional<Page<Topic>> findAllVisibleTopics(Pageable pagination);

    Optional<Page<Topic>> findByStatus(String status, Pageable pagination);
}
