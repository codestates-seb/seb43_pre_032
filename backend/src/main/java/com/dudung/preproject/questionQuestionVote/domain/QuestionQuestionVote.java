package com.dudung.preproject.questionQuestionVote.domain;

import com.dudung.preproject.questionVote.domain.QuestionVote;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
public class QuestionQuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionQuestionVoteId;

    @OneToMany
    private List<QuestionVote> questionVotes = new ArrayList<>();
}