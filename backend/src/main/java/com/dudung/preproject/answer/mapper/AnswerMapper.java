package com.dudung.preproject.answer.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchAnswer(AnswerDto.Patch requestBody);

    default AnswerDto.Response answerToAnswerResponse(Answer answer) {
        return AnswerDto.Response.builder()
                .answerId(answer.getAnswerId())
                .questionId(answer.getQuestion().getQuestionId())
                .memberId(answer.getMember().getMemberId())
                .memberName(answer.getMember().getName())
                .memberReputation(answer.getMember().getReputation())
                .answerContent(answer.getAnswerContent())
                .modifiedAt(answer.getModifiedAt()).build();
    }

    default AnswerDto.ResponseForList answerToAnswerResponseForList(Answer answer) {
        return AnswerDto.ResponseForList.builder()
                .answerId(answer.getAnswerId())
                .answerContent(answer.getAnswerContent())
                .answerVoteSum(answer.getAnswerVoteSum())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .memberId(answer.getMember().getMemberId())
                .memberName(answer.getMember().getName())
                .memberReputation(answer.getMember().getReputation())
                .build();
    }

    default List<AnswerDto.ResponseForList> answerToAnswerResponse(List<Answer> answers) {
        return answers.stream()
                .map(answer -> answerToAnswerResponseForList(answer))
                .collect(Collectors.toList());
    }




}
