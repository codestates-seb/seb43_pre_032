package com.dudung.preproject.memberquestion;

import com.dudung.preproject.question.domain.Question;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Table(name = "memberquestion")
public class memberQuestion {
    @Id
    private Long memberQuestionId;

    @OneToMany(mappedBy = "memberquestion")
    private List<Question> question;
}
