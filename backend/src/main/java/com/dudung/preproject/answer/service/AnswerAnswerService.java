package com.dudung.preproject.answer.service;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.repository.AnswerAnswerRepository;
import com.dudung.preproject.answer.repository.AnswerRepository;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import com.dudung.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerAnswerService {

    private final AnswerAnswerRepository answerAnswerRepository;
    private final MemberService memberService;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public AnswerAnswer createAnswerAnswer(AnswerAnswer answerAnswer, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Answer answer = answerService.findAnswer(answerAnswer.getAnswer().getAnswerId());
        Member member = memberService.findMember(authenticationMemberId);
        answerAnswer.setAnswer(answer);
        answerAnswer.setMember(member);

        AnswerAnswer createdAnswerAnswer = answerAnswerRepository.save(answerAnswer);
        answer.addAnswerAnswer(createdAnswerAnswer);
        member.addAnswerAnswer(createdAnswerAnswer);
        answerRepository.save(answer);
        memberRepository.save(member);

        return createdAnswerAnswer;
    }

    public AnswerAnswer updateAnswerAnswer (AnswerAnswer answerAnswer, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);

        AnswerAnswer findAnswerAnswer = findVerifiedAnswerAnswer(answerAnswer.getAnswerAnswerId());

        patchPermission(findAnswerAnswer, memberService.findMember((authenticationMemberId)));

        findAnswerAnswer.setAnswerAnswerContent(answerAnswer.getAnswerAnswerContent());
        findAnswerAnswer.setAnswerAnswerModifiedAt(answerAnswer.getAnswerAnswerModifiedAt());

        return answerAnswerRepository.save(findAnswerAnswer);
    }

    public AnswerAnswer findAnswerAnswer(Long answeranswerId) {
        return findVerifiedAnswerAnswer(answeranswerId);
    }



    public void deleteAnswerAnswer(Long answeranswerId, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        AnswerAnswer findAnswerAnswer = findVerifiedAnswerAnswer(answeranswerId);
        deletePermission(findAnswerAnswer, memberService.findMember(authenticationMemberId));
        answerAnswerRepository.delete(findAnswerAnswer);
    }

    private AnswerAnswer findVerifiedAnswerAnswer(long authenticationMemberId) {
        Optional<AnswerAnswer> optionalAnswerAnswer = answerAnswerRepository.findById(authenticationMemberId);
        AnswerAnswer findedAnswerAnswer = optionalAnswerAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findedAnswerAnswer;
    }
    private void checkVerifiedId(long authenticationMemberId) {
        if (authenticationMemberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }


    private void patchPermission(AnswerAnswer answerAnswer, Member member) {
        if (!answerAnswer.getMember().getMemberId().equals(member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    private void deletePermission(AnswerAnswer answerAnswer, Member member) {
        if (!answerAnswer.getMember().getMemberId().equals(member.getMemberId()) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }
}
