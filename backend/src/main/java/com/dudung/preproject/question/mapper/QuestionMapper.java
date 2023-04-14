package com.dudung.preproject.question.mapper;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

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
                .viewCount(question.getViewCount())
                .memberName(question.getMember().getName()).build();
    }

    default QuestionDto.ResponseForList questionToQuestionResponseForList(Question question) {
        return QuestionDto.ResponseForList.builder()
                .questionTitle(question.getQuestionTitle())
                .viewCount(question.getViewCount())
                .questionVoteSum(question.getQuestionVoteSum())
                .createdAt(question.getCreatedAt())
                .memberName(question.getMember().getName())
                .answerCount(question.getAnswerCount()).build();
    }
    default List<QuestionDto.ResponseForList> questionsToQuestionsResponse(List<Question> questions) {
        return questions.stream()
                .map(question -> questionToQuestionResponseForList(question))
                .collect(Collectors.toList());
    }
}
