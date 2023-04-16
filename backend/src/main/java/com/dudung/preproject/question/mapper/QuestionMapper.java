package com.dudung.preproject.question.mapper;

import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
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
        QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .questionVoteSum(question.getQuestionVoteSum())
                .viewCount(question.getViewCount())
                .memberName(question.getMember().getName()).build();
        AnswerDto.Response answerResponseDto = question.getAnswers().size() == 0 ? null :
                AnswerDto.Response.builder()   // answersResponse 가 구현이 안돼있어 answerResponse 로 대체
                        .answerId(question.getAnswers().get(0).getAnswerId())
                        .questionId(question.getAnswers().get(0).getQuestion().getQuestionId())
                        .memberName(question.getAnswers().get(0).getMember().getName())
                        .answerContent(question.getAnswers().get(0).getAnswerContent())
                        .modifiedAt(question.getAnswers().get(0).getModifiedAt())
                        .build();

        return new QuestionDto.Response(questionResponseDto, answerResponseDto);
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
