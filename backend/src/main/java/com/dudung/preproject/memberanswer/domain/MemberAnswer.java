package com.dudung.preproject.memberanswer.domain;

import com.dudung.preproject.answer.domain.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class MemberAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberAnswerId;

    @OneToMany
    private List<Answer> answers;
}
