package com.dudung.preproject.answerVote.domain;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Setter
public class AnswerVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerVoteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private AnswerVoteStatus answerVoteStatus = AnswerVoteStatus.ZERO;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;
    @Getter
    public enum AnswerVoteStatus {
        ZERO(0, 0),
        PLUS(1, 10),
        MINUS(-1, -2);

        @Getter
        private int score;
        @Getter
        private int reputation;

        AnswerVoteStatus(int score, int reputation) {
            this.score = score;
            this.reputation = reputation;
        }
    }


}
