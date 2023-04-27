package com.dudung.preproject.answer.repository;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.tag.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a JOIN a.question q WHERE q = :question")
    Page<Answer> findAllByQuestion(Question question, Pageable pageable);
}
