package com.dudung.preproject.questionVote.domain;

import com.dudung.preproject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int score;
}
