package com.dudung.preproject.question.domain;

import com.dudung.preproject.tag.domain.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionTagId;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    public void addQuestion(Question question) {
        this.question = question;
        if(!question.getQuestionTags().contains(this)){
            question.getQuestionTags().add(this);
        }
    }
    public void addTag(Tag tag) {
        this.tag = tag;
        if(!tag.getQuestionTags().contains(this)){
            tag.getQuestionTags().add(this);
        }
    }
}
