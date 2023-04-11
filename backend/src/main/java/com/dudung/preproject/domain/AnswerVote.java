package com.dudung.preproject.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class AnswerVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerVoteId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberId;

    private int countAnswerVote;


}
