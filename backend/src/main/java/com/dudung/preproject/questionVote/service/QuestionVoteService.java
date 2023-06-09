package com.dudung.preproject.questionVote.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.repository.QuestionRepository;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.questionVote.repository.QuestionVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {
    private final QuestionRepository questionRepository;
    private final QuestionVoteRepository questionVoteRepository;

    public void questionVoteUp(Member member, Question question) {
        QuestionVote questionVote = findQuestionVote(member.getQuestionVotes(), question);
        questionVotePlus(questionVote);
        questionVote.setMember(member);
        questionVote.setQuestion(question);
        questionVoteRepository.save(questionVote);
        question.getQuestionVoteSum();
        questionRepository.save(question);
    }

    public void questionVoteDown(Member member, Question question) {
        QuestionVote questionVote = findQuestionVote(member.getQuestionVotes(), question);
        questionVoteMinus(questionVote);
        questionVote.setMember(member);
        questionVote.setQuestion(question);
        questionVoteRepository.save(questionVote);
        question.getQuestionVoteSum();
        questionRepository.save(question);
    }

    private QuestionVote findQuestionVote(List<QuestionVote> questionVotes, Question question) {
        Iterator<QuestionVote> iterator = questionVotes.iterator();
        while(iterator.hasNext()) {
            QuestionVote nextVote = iterator.next();
            if (nextVote.getQuestion().getQuestionId().equals(question.getQuestionId())) {
                return nextVote;
            }
        }
        return new QuestionVote();
    }

    private void questionVotePlus(QuestionVote questionVote) {
        if (questionVote.getQuestionVoteStatus().getScore() == 0) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.PLUS);
        } else if (questionVote.getQuestionVoteStatus().getScore() == 1) {

        } else {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.ZERO);
        }
    }

    private void questionVoteMinus(QuestionVote questionVote) {
        if (questionVote.getQuestionVoteStatus().getScore() == 0) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.MINUS);
        } else if (questionVote.getQuestionVoteStatus().getScore() == 1) {
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.ZERO);
        } else {

        }
    }

    public void checkVerifiedId(long authenticationMemeberId) { // 임의로 넣은 값 '-1'
        if (authenticationMemeberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }

}

