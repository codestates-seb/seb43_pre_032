package com.dudung.preproject.question.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.dto.AnswerAnswerDto;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionAnswer;
import com.dudung.preproject.question.domain.QuestionTag;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import com.dudung.preproject.question.dto.QuestionTagDto;
import com.dudung.preproject.tag.domain.Tag;
import org.mapstruct.Mapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    default Question questionPostToQuestion(QuestionDto.Post requestBody) {
        Question question = new Question();
        Member member = new Member();

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
    default Question questionPatchToQuestion(QuestionDto.Patch requestBody) {
        Question question = new Question();
        Member member = new Member();

        question.setQuestionId(requestBody.getQuestionId());
        question.setQuestionTitle(requestBody.getQuestionTitle());
        question.setQuestionContent(requestBody.getQuestionContent());
        question.setModifiedAt(requestBody.getModifiedAt());

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
                .questionAnswers(questionAnswerForList(question.getQuestionAnswers()))
                .questionVoteSum(question.getQuestionVoteSum())
                .viewCount(question.getViewCount())
                .memberId(question.getMember().getMemberId())
                .memberJpegUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + question.getMember().getMemberId() + "/" + question.getMember().getMemberId() + ".jpeg")
                        .build().toUri().toString())
                .memberPngUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + question.getMember().getMemberId() + "/" + question.getMember().getMemberId() + ".png")
                        .build().toUri().toString())
                .memberName(question.getMember().getName())
                .memberReputation(question.getMember().getReputation()).build();


        return new QuestionDto.Response(questionResponseDto, answerResponseDto);
    }
    default List<QuestionAnswerDto.Response> questionAnswerForList(List<QuestionAnswer> questionAnswers) {
        return questionAnswers.stream()
                .map(questionAnswer -> questionAnswerToResponse(questionAnswer))
                .collect(Collectors.toList());
    }
    default QuestionAnswerDto.Response questionAnswerToResponse(QuestionAnswer questionAnswer) {
        return QuestionAnswerDto.Response.builder()
                .questionAnswerId(questionAnswer.getQuestionAnswerId())
                .questionAnswerContent(questionAnswer.getQuestionAnswerContent())
                .createdAt(questionAnswer.getCreatedAt())
                .modifiedAt(questionAnswer.getModifiedAt())
                .memberId(questionAnswer.getMember().getMemberId())
                .memberJpegUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + questionAnswer.getMember().getMemberId() + "/" + questionAnswer.getMember().getMemberId() + ".jpeg")
                        .build().toUri().toString())
                .memberPngUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + questionAnswer.getMember().getMemberId() + "/" + questionAnswer.getMember().getMemberId() + ".png")
                        .build().toUri().toString())
                .memberName(questionAnswer.getMember().getName())
                .memberReputation(questionAnswer.getMember().getReputation()).build();
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
                .memberId(answer.getMember().getMemberId())
                .memberJpegUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + answer.getMember().getMemberId() + "/" + answer.getMember().getMemberId() + ".jpeg")
                        .build().toUri().toString())
                .memberPngUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + answer.getMember().getMemberId() + "/" + answer.getMember().getMemberId() + ".png")
                        .build().toUri().toString())
                .memberName(answer.getMember().getName())
                .memberReputation(answer.getMember().getReputation())
                .answerAnswers(answerAnswerForList(answer.getAnswerAnswers()))
                .build();
    }
    default List<AnswerAnswerDto.Response> answerAnswerForList(List<AnswerAnswer> answerAnswers) {
        return answerAnswers.stream()
                .map(answerAnswer -> answerAnswerToResponse(answerAnswer))
                .collect(Collectors.toList());
    }
    default AnswerAnswerDto.Response answerAnswerToResponse(AnswerAnswer answerAnswer) {
        return AnswerAnswerDto.Response.builder()
                .answerAnswerId(answerAnswer.getAnswerAnswerId())
                .answerAnswerContent(answerAnswer.getAnswerAnswerContent())
                .createdAt(answerAnswer.getAnswerAnswerModifiedAt())
                .modifiedAt(answerAnswer.getAnswerAnswerModifiedAt())
                .memberId(answerAnswer.getMember().getMemberId())
                .memberJpegUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + answerAnswer.getMember().getMemberId() + "/" + answerAnswer.getMember().getMemberId() + ".jpeg")
                        .build().toUri().toString())
                .memberPngUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + answerAnswer.getMember().getMemberId() + "/" + answerAnswer.getMember().getMemberId() + ".png")
                        .build().toUri().toString())
                .memberName(answerAnswer.getMember().getName())
                .memberReputation(answerAnswer.getMember().getReputation()).build();
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
                .lastStatus(question.getQuestionLastStatus())
                .lastStatusTime(question.getQuestionLastStatusTime())
                .memberId(question.getMember().getMemberId())
                .memberJpegUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + question.getMember().getMemberId() + "/" + question.getMember().getMemberId() + ".jpeg")
                        .build().toUri().toString())
                .memberPngUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/" + question.getMember().getMemberId() + "/" + question.getMember().getMemberId() + ".png")
                        .build().toUri().toString())
                .memberName(question.getMember().getName())
                .memberReputation(question.getMember().getReputation())
                .answerCount(question.getAnswerCount()).build();
    }
    default List<QuestionDto.ResponseForList> questionsToQuestionsResponse(List<Question> questions) {
        return questions.stream()
                .map(question -> questionToQuestionResponseForList(question))
                .collect(Collectors.toList());
    }
}
