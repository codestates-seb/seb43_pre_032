package com.dudung.preproject.question.domain;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.tag.domain.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private int viewCount;

    private int answerCount;
    private String questionTitle;

    private String questionContent;

    @OneToMany(mappedBy = "question")
    private List<QuestionVote> questionVotes = new ArrayList<>();

    public int getQuestionVoteSum() {

        int questionVoteSum = this.questionVotes.stream()
                .map(answerVote -> answerVote.getQuestionVoteStatus().getScore())
                .mapToInt(N -> N)
                .sum();

        return questionVoteSum;
    }

    @OneToMany
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public void addQuestionVote(QuestionVote questionVote) {
        this.questionVotes.add(questionVote);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

}
