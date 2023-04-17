package com.dudung.preproject.questionVote.service;

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
    private QuestionRepository questionRepository;
    private QuestionVoteRepository questionVoteRepository;

    public void questionVoteUp(Member member, Question question) {
        QuestionVote questionVote = findQuestionVote(member.getQuestionVotes(), question);
        questionVotePlus(questionVote);
        questionVote.setMember(member);
        questionVote.setQuestion(question);
        questionVoteRepository.save(questionVote);
        questionRepository.save(question);
    }

    public void questionVoteDown(Member member, Question question) {
        QuestionVote questionVote = findQuestionVote(member.getQuestionVotes(), question);
        questionVoteMinus(questionVote);
        questionVote.setMember(member);
        questionVote.setQuestion(question);
        questionVoteRepository.save(questionVote);
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
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.ZERO);
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
            questionVote.setQuestionVoteStatus(QuestionVote.QuestionVoteStatus.ZERO);
        }
    }

}

