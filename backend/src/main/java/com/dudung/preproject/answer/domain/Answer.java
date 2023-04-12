package com.dudung.preproject.answer.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
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
