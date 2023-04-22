package com.dudung.preproject.member.service;

import com.dudung.preproject.auth.utils.CustomAuthorityUtils;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    public Member createMember(Member member) {

        verifyExistEmailAndName(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

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

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findedMember = findVerifiedMember(memberId);
        memberRepository.delete(findedMember);
    }

    private void verifyExistEmailAndName(String email) {
        Optional<Member> findedMemberByEamil = memberRepository.findByEmail(email);
        if (findedMemberByEamil.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }
    }

    public Member findVerifiedMember (long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findedMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findedMember;
    }

    public Boolean uploading(MultipartFile file, String dirName) {
        Boolean result = Boolean.TRUE;
        try {
            File folder = new File(dirName);
            if (!folder.exists()) folder.mkdirs();

            File destination = new File(folder.getAbsolutePath() , file.getOriginalFilename());
            file.transferTo(destination);

            result = Boolean.FALSE;
        }catch (Exception e) {
            System.out.println("step 3");
            e.printStackTrace();
        } finally {
            System.out.println("step 4");
            return result;
        }
    }
}
