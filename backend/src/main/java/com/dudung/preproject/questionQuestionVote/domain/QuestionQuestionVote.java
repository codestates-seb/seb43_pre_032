package com.dudung.preproject.questionQuestionVote.domain;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table
public class QuestionQuestionVote {
    @Id
    private Long questionQuestionVoteId;

    @OneToMany
    private List<QuestionVote> questionVotes;
}