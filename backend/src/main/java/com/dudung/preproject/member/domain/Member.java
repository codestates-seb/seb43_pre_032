package com.dudung.preproject.member.domain;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String name;

    private LocalDateTime createdAt;

    @OneToMany
    private List<Question> questions = new ArrayList<>();

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<QuestionVote> questionVotes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<AnswerVote> answerVotes = new ArrayList<>();

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addQuestionVote(QuestionVote questionVote) {
        this.questionVotes.add(questionVote);
    }

    public void addAnswerVote(AnswerVote answerVote) {
        this.answerVotes.add(answerVote);
    }


}
