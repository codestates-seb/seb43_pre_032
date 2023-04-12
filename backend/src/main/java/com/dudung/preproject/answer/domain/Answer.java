package com.dudung.preproject.answer.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Answer {

    @Id
    private Long answerId;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member member;

    private String answerContent;

    @OneToMany
    private List<AnswerVote> answerVotes = new ArrayList<>();

    private int answerVoteSum;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public void addAnswerVote(AnswerVote answerVote) {
        this.answerVotes.add(answerVote);
    }

}
