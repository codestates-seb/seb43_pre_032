package com.dudung.preproject.member.service;

import com.dudung.preproject.auth.utils.CustomAuthorityUtils;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import com.dudung.preproject.question.domain.Question;
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


    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Member findMember(String email) {
        return memberRepository.findByEmail(email).get();
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Member findedMember = findVerifiedMember(memberId);
        deletePermission(findedMember, authenticationMemberId);
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

    public void findVerifiedMemberEmail (String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }
    }

    private void checkVerifiedId(long authenticationMemeberId) {
        if (authenticationMemeberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }

    private void patchPermission(Member member, long authenticationMemberId) {
        if (!member.getMemberId().equals(authenticationMemberId) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    private void deletePermission(Member member, long authenticationMemberId) {
        if (!member.getMemberId().equals(authenticationMemberId) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    private void uploadingPermission(Member member, long authenticationMemberId) {
        if (!member.getMemberId().equals(authenticationMemberId) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    public Boolean uploading(MultipartFile file, long memberId, long authenticationMemberId, String url) {

        checkVerifiedId(authenticationMemberId);
        Member findedmember = findVerifiedMember(memberId);
        uploadingPermission(findedmember, authenticationMemberId);

        Boolean result = Boolean.TRUE;

        String dir = Long.toString(memberId);
        String extiension = Optional.ofNullable(file)
                .map(MultipartFile::getOriginalFilename)
                .map(name -> name.substring(name.lastIndexOf(".")).toLowerCase())
                .orElse("default_extension");

        String newFileName = dir + extiension;

        try {
            File folder = new File(url + File.separator + dir);
            File[] files = folder.listFiles();
            if (!folder.exists()) {
                folder.mkdirs();
            } else if (files != null) {
                for (File file1 : files) {
                    file1.delete();
                }
            }
            File destination = new File( folder , newFileName);

            System.out.println(folder.getAbsolutePath());
            file.transferTo(destination);

            result = Boolean.FALSE;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    public Member updateMyPage(Member member, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Member findedMember = findVerifiedMember(member.getMemberId());
        findVerifiedMemberEmail(member.getEmail());

        patchPermission(findedMember, authenticationMemberId);

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findedMember.setName(member.getName()));
        Optional.ofNullable(member.getMyPageTitle())
                .ifPresent(myPageTitle -> findedMember.setMyPageTitle(member.getMyPageTitle()));
        Optional.ofNullable(member.getAboutMe())
                .ifPresent(aboutMe -> findedMember.setAboutMe(member.getAboutMe()));
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findedMember.setEmail(member.getEmail()));

        findedMember.setModifiedAt(member.getModifiedAt());

        return memberRepository.save(findedMember);
    }

}
