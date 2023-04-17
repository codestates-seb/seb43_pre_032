package com.dudung.preproject.answerAnswerVote.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
public class AnswerAnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerAnswerVoteId;

    @OneToMany
    private List<AnswerVote> answerVotes = new ArrayList<>();
}
