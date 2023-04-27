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
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String answerContent;

    @OneToMany(mappedBy = "answer")
    private List<AnswerVote> answerVotes = new ArrayList<>();

    private int answerVoteSum;

    public int getAnswerVoteSum() {

        int answerVoteSum = this.answerVotes.stream()
                .map(answerVote -> answerVote
                        .getAnswerVoteStatus().getScore())
                .mapToInt(N -> N)
                .sum();
        this.answerVoteSum = answerVoteSum;
        return answerVoteSum;
    }

    @OneToMany(mappedBy = "answer")
    private List<AnswerAnswer> answerAnswers = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    public void addAnswerVote(AnswerVote answerVote) {
        this.answerVotes.add(answerVote);
    }

    public void addAnswerAnswer(AnswerAnswer answerAnswer) {
        this.answerAnswers.add(answerAnswer);
    }
}
