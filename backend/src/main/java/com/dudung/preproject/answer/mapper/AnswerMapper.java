package com.dudung.preproject.answer.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchAnswer(AnswerDto.Patch requestBody);

    AnswerDto.Response answerToAnswerResponse(Answer answer);


}
