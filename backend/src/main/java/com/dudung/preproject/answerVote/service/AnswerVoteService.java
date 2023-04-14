package com.dudung.preproject.answerVote.service;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    public void voteUp(Member member, Answer answer) {
        AnswerVote answerVote = findAnswerVote(member.getAnswerVotes(), answer);
        plus(answerVote);
    }

    public void voteDown(Member member, Answer answer) {
        AnswerVote answerVote = findAnswerVote(member.getAnswerVotes(), answer);
        minus(answerVote);
    }

    private AnswerVote findAnswerVote(List<AnswerVote> answerVotes, Answer answer) {
        Iterator<AnswerVote> iterator = answerVotes.iterator();
        while(iterator.hasNext()) {
            AnswerVote nextVote = iterator.next();
            if (nextVote.getAnswer().equals(answer)) {
                return nextVote;
            }
        }
        return new AnswerVote();
    }

    private void plus(AnswerVote answerVote) {
        if (answerVote.getAnswerVoteStatus().getScore() == 0) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.PLUS);
        } else {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.ZERO);
        }
    }

    private void minus(AnswerVote answerVote) {
        if (answerVote.getAnswerVoteStatus().getScore() == 0) {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.MINUS);
        } else {
            answerVote.setAnswerVoteStatus(AnswerVote.AnswerVoteStatus.ZERO);
        }
    }
}
