package com.dudung.preproject.question.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionTag;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import com.dudung.preproject.question.dto.QuestionTagDto;
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

        List<QuestionTag> questionTags = requestBody.getTagName().stream()
                .map(ADD -> {
                    Tag tag = new Tag();
                    QuestionTag questionTag = new QuestionTag();
                    tag.setTagId(ADD.getTagId());
                    questionTag.addTag(tag);
                    questionTag.addQuestion(question);
                    return questionTag;
                }).collect(Collectors.toList());
        question.setQuestionTags(questionTags);
        question.setMember(member);

        return question;
    }
    Question questionPatchToQuestion(QuestionDto.Patch requestBody);
    default QuestionDto.Response questionToQuestionResponse(Question question, List<Answer> answers) {
        List<AnswerDto.ResponseForList> answerResponseDto = answers.size() == 0 ? null :
                question.getAnswers().stream()
                        .map(answer -> answerToAnswerResponseForList(answer))
                        .collect(Collectors.toList());

        QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .tagName(tagsToTagNames(question.getQuestionTags()))
                .questionVoteSum(question.getQuestionVoteSum())
                .viewCount(question.getViewCount())
                .memberName(question.getMember().getName()).build();


        return new QuestionDto.Response(questionResponseDto, answerResponseDto);
    }
    default List<QuestionTagDto.Response> tagsToTagNames(List<QuestionTag> questionTagList) {
        return questionTagList.stream()
                .map(questionTag -> tagToTagName(questionTag))
                .collect(Collectors.toList());
    }

    default QuestionTagDto.Response tagToTagName(QuestionTag questionTag) {
        return QuestionTagDto.Response.builder()
                .tagId(questionTag.getTag().getTagId())
                .tagName(questionTag.getTag().getTagName())
                .build();
    }

    default AnswerDto.ResponseForList answerToAnswerResponseForList(Answer answer) {
        return AnswerDto.ResponseForList.builder()
                .answerId(answer.getAnswerId())
                .answerContent(answer.getAnswerContent())
                .answerVoteSum(answer.getAnswerVoteSum())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .answerName(answer.getMember().getName())
                .build();
    }

    default QuestionDto.ResponseForList questionToQuestionResponseForList(Question question) {
        return QuestionDto.ResponseForList.builder()
                .questionId(question.getQuestionId())
                .questionTitle(question.getQuestionTitle())
                .questionContent(question.getQuestionContent())
                .viewCount(question.getViewCount())
                .questionVoteSum(question.getQuestionVoteSum())
                .createdAt(question.getCreatedAt())
                .tagName(tagsToTagNames(question.getQuestionTags()))
                .memberName(question.getMember().getName())
                .answerCount(question.getAnswerCount()).build();
    }
    default List<QuestionDto.ResponseForList> questionsToQuestionsResponse(List<Question> questions) {
        return questions.stream()
                .map(question -> questionToQuestionResponseForList(question))
                .collect(Collectors.toList());
    }
}
