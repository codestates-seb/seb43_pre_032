package com.dudung.preproject.member.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        verifyExistEmailAndName(member.getEmail(), member.getName());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member findedMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findedMember.setEmail(email));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findedMember.setPassword(password));
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findedMember.setName(name));

        return memberRepository.save(findedMember);
    }

    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public void deleteMember(long memberId) {
        Member findedMember = findVerifiedMember(memberId);
        memberRepository.delete(findedMember);
    }

    private void verifyExistEmailAndName(String email, String name) {
        Optional<Member> findedMemberByEamil = memberRepository.findByEmail(email);
        if (findedMemberByEamil.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }
        Optional<Member> findedMemberByName = memberRepository.findByName(name);
        if (findedMemberByName.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NAME_EXISTS);
        }
    }

    public Member findVerifiedMember (long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findedMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findedMember;
    }
}
