package com.dudung.preproject.member.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuestionVote questionVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerVote_id")
    private AnswerVote answerVote;


}
