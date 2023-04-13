package com.dudung.preproject.answer.service;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.repository.AnswerRepository;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;


    public Answer createAnswer(Answer answer) {

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getQuestion())
                .ifPresent(question -> findAnswer.setQuestion(question));
        Optional.ofNullable(answer.getModifiedAt())
                .ifPresent(LocalDateTime -> findAnswer.setModifiedAt(java.time.LocalDateTime.now()));

        return answerRepository.save(findAnswer);
    }

    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findedAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findedAnswer;
    }

    public Answer findAnswer(Long answerId) {
        return findVerifiedAnswer(answerId);
    }

    public void deleteAnswer(Long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        answerRepository.delete(findAnswer);
    }
}
