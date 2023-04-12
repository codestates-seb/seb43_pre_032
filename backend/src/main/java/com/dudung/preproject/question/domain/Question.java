package com.dudung.preproject.question.domain;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.Getter;

import javax.persistence.*;
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
