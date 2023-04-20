package com.dudung.preproject.tag.mapper;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionTag;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionTagDto;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.dto.TagDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {
    List<TagDto.ResponseForList> tagsToTagsResponse(List<Tag> tagList);

    default TagDto.Response tagTotagResponse(Tag tag, List<Question> questions) {
        List<QuestionDto.ResponseForList> responseQuestions = questions.stream()
                .map(question -> questionToQuestionResponseForList(question))
                .collect(Collectors.toList());
        return TagDto.Response.builder()
                .tagId(tag.getTagId())
                .tagName(tag.getTagName())
                .tagDescription(tag.getTagDescription())
                .questions(responseQuestions).build();
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
                .memberId(question.getMember().getMemberId())
                .memberName(question.getMember().getName())
                .memberReputation(question.getMember().getReputation())
                .answerCount(question.getAnswerCount()).build();
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

}
