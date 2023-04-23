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
    public int getAnswerCount() {
        return this.answers.size();
    }
    private String questionTitle;

    private String questionContent;

    @OneToMany(mappedBy = "question")
    private List<QuestionVote> questionVotes = new ArrayList<>();
    @Column
    private int questionVoteSum;
    public int getQuestionVoteSum() {

        int questionVoteSum = this.questionVotes.stream()
                .map(answerVote -> answerVote.getQuestionVoteStatus().getScore())
                .mapToInt(N -> N)
                .sum();

        this.questionVoteSum = questionVoteSum;

        return questionVoteSum;
    }

    @OneToMany(mappedBy = "question")
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public void addQuestionTag(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
    }
    public void addQuestionVote(QuestionVote questionVote) {
        this.questionVotes.add(questionVote);
    }
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
    public void addQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswers.add(questionAnswer);
    }

}
