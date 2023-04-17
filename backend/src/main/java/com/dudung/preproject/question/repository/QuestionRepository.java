package com.dudung.preproject.question.repository;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.tag.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByTagsContains(Tag tag, Pageable pageable);
}
