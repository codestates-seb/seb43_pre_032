package com.dudung.preproject.question.repository;

import com.dudung.preproject.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
