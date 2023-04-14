package com.dudung.preproject.question.mapper;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostToQuestion(QuestionDto.Post requestBody);
    Question questionPatchToQuestion(QuestionDto.Patch requestBody);
    @Mapping(source = "memberName", target = "question.member.memberName")
    QuestionDto.Response questionToQuestionResponse(Question question);
}
