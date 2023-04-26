package com.dudung.preproject.tag.domain;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionTag;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String tagName;

    private String tagDescription;

    private int questions;

    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionTags = new ArrayList<>();

    public int getQuestionNum() {
        this.questions = this.questionTags.size();
        return this.questions;
    }

    public void addQuestionTag(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
        if(!questionTag.getTag().equals(this)) {
            questionTag.setTag(this);
        }
    }

}
