package com.dudung.preproject.member.mapper;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {
    Member memeberPostToMember(MemberDto.Post requestBody);

    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.Response memberToMemberResponse(Member member);
}
