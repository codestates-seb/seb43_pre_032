package com.dudung.preproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @OneToMany
    private List<QuestionVote> questionVote;

    @ManyToOne
    private Tag tag;

    @OneToMany
    private Answer answer;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
