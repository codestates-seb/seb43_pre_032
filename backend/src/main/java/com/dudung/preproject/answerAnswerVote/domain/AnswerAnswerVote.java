package com.dudung.preproject.answerAnswerVote.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Table
public class AnswerAnswerVote {
    @Id
    private Long answerAnswerVoteId;

    @OneToMany
    private List<AnswerVote> answerVotes;
}
