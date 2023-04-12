package com.dudung.preproject.tag.domain;

import com.dudung.preproject.question.domain.Question;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String tagName;

    private String tagDescription;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
