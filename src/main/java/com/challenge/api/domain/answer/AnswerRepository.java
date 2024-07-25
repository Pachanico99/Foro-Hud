package com.challenge.api.domain.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    @Query("""
            SELECT a FROM Answer a WHERE a.assessment != 'NOT_USEFUL'
           """)
    Optional<Page<Answer>> findAllVisibleAnswers(Pageable pagination);
}
