package com.dudung.preproject.answerVote.repository;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {

}
