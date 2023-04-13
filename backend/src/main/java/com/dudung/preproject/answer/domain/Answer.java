package com.dudung.preproject.answer.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member member;

    private String answerContent;

    @OneToMany
    private List<AnswerVote> answerVotes = new ArrayList<>();

    private int answerVoteSum;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    public void addAnswerVote(AnswerVote answerVote) {
        this.answerVotes.add(answerVote);
    }

}
