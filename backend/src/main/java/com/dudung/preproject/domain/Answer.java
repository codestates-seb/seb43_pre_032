package com.dudung.preproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Answer {

    @Id
    private Long answerId;

    private Question question;

    private Member member;

    private AnswerVote answerVote;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
