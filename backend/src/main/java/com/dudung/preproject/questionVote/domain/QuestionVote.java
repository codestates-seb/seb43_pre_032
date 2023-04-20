package com.dudung.preproject.questionVote.domain;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    private QuestionVoteStatus questionVoteStatus = QuestionVoteStatus.ZERO;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public enum QuestionVoteStatus {
        ZERO(0, 0),
        PLUS(1, 5),
        MINUS(-1, -2);

        @Getter
        private int score;
        @Getter
        private int reputation;

        QuestionVoteStatus(int score, int reputation) {
            this.score = score;
            this.reputation = reputation;
        }
    }

}
