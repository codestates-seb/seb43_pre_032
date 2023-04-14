package com.dudung.preproject.memberquestion.domain;

import com.dudung.preproject.question.domain.Question;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
public class MemberQuestion {
    @Id
    private Long memberQuestionId;

    @OneToMany
    private List<Question> question = new ArrayList<>();
}
