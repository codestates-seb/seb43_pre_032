package com.dudung.preproject.memberanswervote.domain;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class MemberAnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberAnswerId;

    @OneToMany
    private List<AnswerVote> answerVotes = new ArrayList<>();
}
