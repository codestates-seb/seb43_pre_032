package com.dudung.preproject.question.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionAnswer;
import com.dudung.preproject.question.repository.QuestionAnswerRepository;
import com.dudung.preproject.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionService questionService;
    private final MemberService memberService;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    public QuestionAnswer createQuestionAnswer(QuestionAnswer questionAnswer, long authenticationMemeberId) {
        checkVerifiedId(authenticationMemeberId);
        Question question = questionService.findQuestion(questionAnswer.getQuestion().getQuestionId());
        Member member = memberService.findMember(authenticationMemeberId);
        questionAnswer.setQuestion(question);
        questionAnswer.setMember(member);

        QuestionAnswer createdQuestionAnswer = questionAnswerRepository.save(questionAnswer);
        question.addQuestionAnswer(createdQuestionAnswer);
        member.addQuestionAnswer(createdQuestionAnswer);
        questionRepository.save(question);
        memberRepository.save(member);

        return createdQuestionAnswer;
    }

    public QuestionAnswer updateQuestionAnswer(QuestionAnswer questionAnswer, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        QuestionAnswer findedQuestionAnswer = findVerifiedQuestionAnswer(questionAnswer.getQuestionAnswerId());

        patchPermission(findedQuestionAnswer, memberService.findMember(authenticationMemberId));

        findedQuestionAnswer.setQuestionAnswerContent(questionAnswer.getQuestionAnswerContent());
        findedQuestionAnswer.setModifiedAt(questionAnswer.getModifiedAt());

        return questionAnswerRepository.save(findedQuestionAnswer);
    }

    public void deleteQuestionAnswer(long questionAnswerId, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        QuestionAnswer findedQuestionAnswer = findVerifiedQuestionAnswer(questionAnswerId);
        deletePermission(findedQuestionAnswer, memberService.findMember(authenticationMemberId));
        questionAnswerRepository.delete(findedQuestionAnswer);
    }

    private QuestionAnswer findVerifiedQuestionAnswer(long questionAnswerId) {
        Optional<QuestionAnswer> optionalQuestionAnswer = questionAnswerRepository.findById(questionAnswerId);
        QuestionAnswer findedQuestionAnswer = optionalQuestionAnswer.orElseThrow(() ->  new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findedQuestionAnswer;
    }

    private void checkVerifiedId(long authenticationMemeberId) { // 임의로 넣은 값 '-1'
        if (authenticationMemeberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }

    private void patchPermission(QuestionAnswer questionAnswer, Member member) {
        if (!questionAnswer.getMember().getMemberId().equals(member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    private void deletePermission(QuestionAnswer questionAnswer, Member member) {
        if (!questionAnswer.getMember().getMemberId().equals(member.getMemberId()) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }
}
