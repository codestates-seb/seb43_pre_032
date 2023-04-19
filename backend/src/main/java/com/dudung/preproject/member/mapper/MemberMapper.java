package com.dudung.preproject.member.mapper;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
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
}
