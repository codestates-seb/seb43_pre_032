package com.dudung.preproject.questionQuestionVote.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "QuestionQuestionVote")
public class QuestionQuestionVote {
    @Id
    private Long questionQuestionVoteId;

    @Id
    private Long questionVoteId;
}