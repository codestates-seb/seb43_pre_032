package com.dudung.preproject.answer.domain;

import com.dudung.preproject.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AnswerAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerAnswerId;
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String answerAnswerContent;
    private LocalDateTime answerAnswerCreateAt;
    private LocalDateTime answerAnswerModifiedAt;
}
