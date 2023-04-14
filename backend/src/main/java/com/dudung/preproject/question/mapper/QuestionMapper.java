package com.dudung.preproject.question.mapper;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "memberId", target = "member.memberId")
    Question questionPostToQuestion(QuestionDto.Post requestBody);
    Question questionPatchToQuestion(QuestionDto.Patch requestBody);
    default QuestionDto.Response questionToQuestionResponse(Question question) {
        return QuestionDto.Response.builder()
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .questionVoteSum(question.getQuestionVoteSum())
                .member(question.getMember().getName()).build();
    }
    List<QuestionDto.ResponseForList> questionsToQuestionsResponse(List<Question> questions);
}
