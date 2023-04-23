package com.dudung.preproject.question.repository;

import com.dudung.preproject.question.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnwerRepository extends JpaRepository<QuestionAnswer, Long> {
}
