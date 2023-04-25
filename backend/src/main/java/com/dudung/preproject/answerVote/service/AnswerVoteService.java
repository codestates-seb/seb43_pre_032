package com.dudung.preproject.answerVote.service;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.repository.AnswerRepository;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.answerVote.repository.AnswerVoteRepository;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerRepository answerRepository;
    private final AnswerVoteRepository answerVoteRepository;

    public void checkVerifiedId(long authenticationMemeberId) { // 임의로 넣은 값 '-1'
        if (authenticationMemeberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }
    public void voteUp(Member member, Answer answer) {
        AnswerVote answerVote = findAnswerVote(member.getAnswerVotes(), answer);
        plus(answerVote);
        answerVote.setAnswer(answer);
        answerVote.setMember(member);
        answerVoteRepository.save(answerVote);
        answer.getAnswerVoteSum();
        answerRepository.save(answer);
    }

    public void voteDown(Member member, Answer answer) {
        AnswerVote answerVote = findAnswerVote(member.getAnswerVotes(), answer);
        minus(answerVote);
        answerVote.setAnswer(answer);
        answerVote.setMember(member);
        answerVoteRepository.save(answerVote);
        answer.getAnswerVoteSum();
        answerRepository.save(answer);
    }

    private AnswerVote findAnswerVote(List<AnswerVote> answerVotes, Answer answer) {
        Iterator<AnswerVote> iterator = answerVotes.iterator();
        while(iterator.hasNext()) {
            AnswerVote nextVote = iterator.next();
            if (nextVote.getAnswer().getAnswerId().equals(answer.getAnswerId())) {
                return nextVote;
            }
        }
        return new AnswerVote();
    }

    private void plus(AnswerVote answerVote) {
        if (answerVote.getAnswerVoteStatus().getScore() == 0) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.PLUS);
        } else if (answerVote.getAnswerVoteStatus().getScore() == 1){

        } else {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.ZERO);
        }
    }

    private void minus(AnswerVote answerVote) {
        if (answerVote.getAnswerVoteStatus().getScore() == 0) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.MINUS);
        } else if (answerVote.getAnswerVoteStatus().getScore() == 1) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.ZERO);
        } else {

        }
    }
}
