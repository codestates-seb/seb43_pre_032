package com.dudung.preproject.question.mapper;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionAnswer;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {
    default QuestionAnswer questionAnswerPostToQuestionAnswer(QuestionAnswerDto.Post requestBody) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        Question question = new Question();
        Member member = new Member();
        question.setQuestionId(requestBody.getQuestionId());
        member.setMemberId(requestBody.getMemberId());
        questionAnswer.setQuestion(question);
        questionAnswer.setMember(member);
        questionAnswer.setQuestionAnswerContent(requestBody.getQuestionAnswerContent());
        questionAnswer.setCreatedAt(LocalDateTime.now());

        return questionAnswer;
    }

    default QuestionAnswer questionAnswerPatchToQuestionAnswer(QuestionAnswerDto.Patch requestBody) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        Question question = new Question();
        Member member = new Member();
        question.setQuestionId(requestBody.getQuestionId());
        member.setMemberId(requestBody.getMemberId());
        questionAnswer.setQuestionAnswerId(requestBody.getQuestionAnswerId());
        questionAnswer.setQuestion(question);
        questionAnswer.setMember(member);
        questionAnswer.setQuestionAnswerContent(requestBody.getQuestionAnswerContent());
        questionAnswer.setModifiedAt(LocalDateTime.now());

        return questionAnswer;
    }
}
