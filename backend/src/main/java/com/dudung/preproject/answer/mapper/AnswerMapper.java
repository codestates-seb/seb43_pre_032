package com.dudung.preproject.answer.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchAnswer(AnswerDto.Patch requestBody);

    @Mapping(target = "memberName", source = "answer.member.name")
    AnswerDto.Response answerToAnswerResponse(Answer answer);



}
