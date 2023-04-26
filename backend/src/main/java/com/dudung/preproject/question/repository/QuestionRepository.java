package com.dudung.preproject.question.repository;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionTag;
import com.dudung.preproject.tag.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q JOIN q.questionTags t WHERE t.tag = :tag")
    Page<Question> findAllByTag(Tag tag, Pageable pageable);
    Page<Question> findAllByQuestionTitleContaining(String keyword, Pageable pageable);
}
