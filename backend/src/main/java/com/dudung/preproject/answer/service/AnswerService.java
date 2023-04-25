package com.dudung.preproject.answer.service;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.repository.AnswerRepository;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.repository.QuestionRepository;
import com.dudung.preproject.question.repository.QuestionTagRepository;
import com.dudung.preproject.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberService memberService;

    public Answer createAnswer(Answer answer, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Member member = memberService.findMember(authenticationMemberId);
        answer.setMember(member);

        Answer createdAnswer = answerRepository.save(answer);
        member.addAnswer(createdAnswer);
        return createdAnswer;
    }

    public Answer updateAnswer(Answer answer, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        patchPermission(findAnswer, memberService.findMember(authenticationMemberId));

        Optional.ofNullable(answer.getQuestion())
                .ifPresent(question -> findAnswer.setQuestion(question));
        Optional.ofNullable(answer.getAnswerContent())
                .ifPresent(answerContent -> findAnswer.setAnswerContent(answerContent));
        Optional.ofNullable(answer.getModifiedAt())
                .ifPresent(LocalDateTime -> findAnswer.setModifiedAt(java.time.LocalDateTime.now()));

        findAnswer.setModifiedAt(answer.getModifiedAt());

        return answerRepository.save(findAnswer);
    }

    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findedAnswer;
    }

    public Answer findAnswer(Long answerId) {

        Answer findedAnswer = findVerifiedAnswer(answerId);

        return findedAnswer;
    }

    public void deleteAnswer(Long answerId, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Answer findAnswer = findVerifiedAnswer(answerId);
        deletePermission(findAnswer, memberService.findMember(authenticationMemberId));
        answerRepository.delete(findAnswer);
    }

    public Page<Answer> findAnswers(int page, String tab) {
        if (tab.equals("score")){
            tab = "answerVoteSum";
        } else if (tab.equals("newest")) {
            tab = "answerId";
        } else if (tab.equals("oldest")) {
            tab = "answerId";
            return answerRepository.findAll(PageRequest.of
                    (page, 5, Sort.by(tab).ascending()));
        }
        return answerRepository.findAll(PageRequest.of
                (page, 5, Sort.by(tab).descending()));
    }

    private void checkVerifiedId(long authenticationMemeberId) {
        if (authenticationMemeberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }

    private void patchPermission(Answer answer, Member member) {
        if (!answer.getMember().getMemberId().equals(member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    private void deletePermission(Answer answer, Member member) {
        if (!answer.getMember().getMemberId().equals(member.getMemberId()) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }
}
