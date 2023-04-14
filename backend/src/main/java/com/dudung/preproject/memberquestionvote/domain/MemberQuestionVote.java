package com.dudung.preproject.memberquestionvote.domain;

import com.dudung.preproject.questionVote.domain.QuestionVote;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MemberQuestionVote {

    @Id
    private Long memberQuestionVote;

    @OneToMany
    private List<QuestionVote> questionVotes = new ArrayList<>();
}

