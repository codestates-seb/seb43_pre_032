package com.dudung.preproject.question.mapper;

import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.dto.TagDto;
import com.dudung.preproject.tag.repository.TagRepository;
import com.dudung.preproject.tag.service.TagService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    default Question questionPostToQuestion(QuestionDto.Post requestBody) {
        Question question = new Question();
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());

        question.setQuestionTitle(requestBody.getQuestionTitle());
        question.setQuestionContent(requestBody.getQuestionContent());
        question.setCreatedAt(requestBody.getCreatedAt());

        List<Tag> tagList = requestBody.getTagName().stream()
                .map(tagName -> {
                    Tag tag = new Tag();
                    tag.setTagId(tagName.getTagId());
                    return tag;
                }).collect(Collectors.toList());
        question.setTags(tagList);
        question.setMember(member);

        return question;
    }
    Question questionPatchToQuestion(QuestionDto.Patch requestBody);
    default QuestionDto.Response questionToQuestionResponse(Question question) {
        QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .tagName(tagsToTagNames(question.getTags()))
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

    List<TagDto.Name> tagsToTagNames(List<Tag> tagList);

    default QuestionDto.ResponseForList questionToQuestionResponseForList(Question question) {
        return QuestionDto.ResponseForList.builder()
                .questionTitle(question.getQuestionTitle())
                .viewCount(question.getViewCount())
                .questionVoteSum(question.getQuestionVoteSum())
                .createdAt(question.getCreatedAt())
                .tagName(tagsToTagNames(question.getTags()))
                .memberName(question.getMember().getName())
                .answerCount(question.getAnswerCount()).build();
    }
    default List<QuestionDto.ResponseForList> questionsToQuestionsResponse(List<Question> questions) {
        return questions.stream()
                .map(question -> questionToQuestionResponseForList(question))
                .collect(Collectors.toList());
    }
}
