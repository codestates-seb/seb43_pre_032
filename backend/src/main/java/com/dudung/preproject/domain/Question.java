package com.dudung.preproject.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @OneToMany(mappedBy = "question")
    @JoinColumn(name = "questionVote_id")
    private QuestionVote questionVote;

    private Tag tag;

    private Answer answer;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
