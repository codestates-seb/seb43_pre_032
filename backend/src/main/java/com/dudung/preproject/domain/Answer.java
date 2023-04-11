package com.dudung.preproject.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member member;

    @OneToMany
    private List<AnswerVote> answerVote;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
