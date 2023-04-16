package com.dudung.preproject.questionVote.repository;

import com.dudung.preproject.questionVote.domain.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
