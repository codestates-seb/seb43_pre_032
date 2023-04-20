package com.dudung.preproject.member.mapper;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memeberPostToMember(MemberDto.Post requestBody);

    Member memberPatchToMember(MemberDto.Patch requestBody);
    default MemberDto.Response memberToMemberResponse(Member member){
        return MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .build();
    }

    default MemberDto.ResponseForList memberToMemberResponseForList(Member member) {
        return MemberDto.ResponseForList.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
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
                .name(member.getName())
                .email(member.getEmail())
                .createAt(member.getCreatedAt())
                .questionCount(member.getQuestionCount())
                .answerCount(member.getAnswerCount())
                .questionTitle(member.getQuestions().stream()
                        .map(question -> getQuestionToMemberList(question))
                        .collect(Collectors.toList()))
                .build();
    }

    default QuestionResponseDto.QuestionMemberResponseForList getQuestionToMemberList(Question question) {
        return QuestionResponseDto.QuestionMemberResponseForList.builder()
                .questionsTitle(question.getQuestionTitle()).build();
    }
}
