package com.dudung.preproject.answer.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchAnswer(AnswerDto.Patch requestBody);

    default AnswerDto.Response answerToAnswerResponse(Answer answer) {
        return AnswerDto.Response.builder()
                .answerId(answer.getAnswerId())
                .questionId(answer.getQuestion().getQuestionId())
                .memberName(answer.getMember().getName())
                .answerContent(answer.getAnswerContent())
                .modifiedAt(answer.getModifiedAt()).build();
    }



}
