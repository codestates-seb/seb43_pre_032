package com.dudung.preproject.question.domain;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.tag.domain.Tag;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    private Member member;

    private int viewCount;

    private int answerCount;

    private String questionContent;

    @OneToMany
    private List<QuestionVote> questionVotes = new ArrayList<>();

    private int questionVoteSum;

    @OneToMany
    private List<Tag> tags = new ArrayList<>();

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public void addQuestionVote(QuestionVote questionVote) {
        this.questionVotes.add(questionVote);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

}
