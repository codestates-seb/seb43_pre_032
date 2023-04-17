package com.dudung.preproject.tag.mapper;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
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
                .questionTitle(question.getQuestionTitle())
                .viewCount(question.getViewCount())
                .questionVoteSum(question.getQuestionVoteSum())
                .createdAt(question.getCreatedAt())
                .tagName(tagsToTagNames(question.getTags()))
                .memberName(question.getMember().getName())
                .answerCount(question.getAnswerCount()).build();
    }

    List<TagDto.Name> tagsToTagNames(List<Tag> tagList);
}
