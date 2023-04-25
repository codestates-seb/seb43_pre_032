package com.dudung.preproject.member.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import org.mapstruct.Mapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memeberPostToMember(MemberDto.Post requestBody);

    Member memberPatchToMember(MemberDto.Patch requestBody);

    Member responserMypagePatchToMember(MemberDto.MyPagePatch requestBody);
    default MemberDto.Response memberToMemberResponse(Member member){
        return MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .reputation(member.getReputation())
                .build();
    }

    default MemberDto.ResponseForList memberToMemberResponseForList(Member member) {
        return MemberDto.ResponseForList.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .reputation(member.getReputation())
                .build();

    }

    default List<MemberDto.ResponseForList> membersToMemberResponses(List<Member> members) {
        return members.stream()
                .map(member -> memberToMemberResponseForList(member))
                .collect(Collectors.toList());
    }

    default MemberDto.ResponseMyPage memberToMyPage(Member member){
        return MemberDto.ResponseMyPage.builder()
                .memberId(member.getMemberId())
                .memberJpegUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/"+member.getMemberId() + File.separator + member.getMemberId() + ".jpeg")
                        .build().toUri().toString())
                .memberPngUrl(UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com")
                        .port(8080)
                        .path("/image/"+member.getMemberId() + File.separator + member.getMemberId() + ".png")
                        .build().toUri().toString())
                .answers(getAnswerToMember(member.getAnswers()))
                .questions(getQuestionToMember(member.getQuestions()))
                .name(member.getName())
                .createAt(member.getCreatedAt())
                .reputation(member.getReputation())
                .questionCount(member.getQuestionCount())
                .answerCount(member.getAnswerCount())
                .build();
    }

    default List<QuestionResponseDto.QuestionMemberResponseForList> getQuestionToMember(List<Question> question) {
        return question.stream()
                .map(questionList -> QuestionResponseDto.QuestionMemberResponseForList.builder()
                        .questionId(questionList.getQuestionId())
                        .questionsTitle(questionList.getQuestionTitle())
                        .build())
                .collect(Collectors.toList());

    }

    default List<AnswerDto.AnswerMemberResponseForList> getAnswerToMember(List<Answer> answer) {
        return answer.stream()
                .map(answerList -> AnswerDto.AnswerMemberResponseForList.builder()
                        .answerId(answerList.getAnswerId())
                        .answerContent(answerList.getAnswerContent())
                        .build())
                .collect(Collectors.toList());
    }

    default AnswerDto.AnswerMemberResponseForList getAnswerToMemberList(Answer answer){
        return AnswerDto.AnswerMemberResponseForList.builder()
                .answerContent(answer.getAnswerContent())
                .answerId(answer.getAnswerId())
                .build();
    }


}
