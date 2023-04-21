package com.dudung.preproject.member.domain;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String name;

    private LocalDateTime createdAt = LocalDateTime.now();

    private int reputation;

    public int getReputation() {
        int qReputation = this.questions.stream()
                .flatMap(question -> question.getQuestionVotes().stream())
                .mapToInt(questionVote -> questionVote.getQuestionVoteStatus().getReputation())
                .sum();

        int aReputation = this.answers.stream()
                .flatMap(answer -> answer.getAnswerVotes().stream())
                .mapToInt(answerVotes -> answerVotes.getAnswerVoteStatus().getReputation())
                .sum();

        int myReputation = this.answerVotes.stream()
                .filter(answerVote -> answerVote.getAnswerVoteStatus().getScore() == -1)
                .mapToInt(answerVote -> answerVote.getAnswerVoteStatus().getScore())
                .sum();


        return qReputation + aReputation + myReputation + 1;
    }

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<QuestionVote> questionVotes = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<AnswerVote> answerVotes = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

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

    public int getQuestionCount() {
        return this.questions.size();
    }

    public int getAnswerCount() {
        return this.answers.size();
    }

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
